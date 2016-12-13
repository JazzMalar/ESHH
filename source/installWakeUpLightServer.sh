#!/bin/bash

mysql_version="mysql-server-5.5"
tomcat_version="tomcat8"
java_version="oracle-java8-installer"

context="ROOT"
sqlFile="02_SQL/WI39_Coding.Datenbankscripts.sql"
warFile="03_Middleware/ROOT.war"

JAVA_HOME="/usr/lib/jvm/java-8-oracle"
CATALINA_HOME="/usr/share/$tomcat_version"
CATALINA_BASE="/var/lib/$tomcat_version"


if [ "$EUID" -ne 0 ] ; then
	echo "Please run as root"
	exit
fi


echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" > /etc/apt/sources.list.d/webupd8team-java.list
echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" >> /etc/apt/sources.list.d/webupd8team-java.list
apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886

apt-get -y -qq  update && apt-get -y -qq upgrade
apt-get -y -qq  install debconf-utils


echo "installing $java_version..."
debconf-set-selections <<< "debconf shared/accepted-oracle-license-v1-1 select true"
debconf-set-selections <<< "debconf shared/accepted-oracle-license-v1-1 seen true"
apt-get -y -qqq install $java_version > /dev/null

export JAVA_HOME

javainstall=$(dpkg --list | grep $java_version | wc -l)
if [ $javainstall -lt 1 ] ; then
	echo "Could not install $java_version. Aborting."
	exit 1; 
fi

echo "adding tomcat user..."
adduser --quiet --system --shell /bin/bash --gecos 'Tomcat Java Servlet and JSP engine' --group --disabled-password --home /home/tomcat $tomcat_version

echo "installing $tomcat_version..."
apt-get -y -qq install $tomcat_version > /dev/null

export CATALINA_HOME
echo "export CATALINA_BASE=$CATALINA_BASE" >> $CATALINA_HOME/bin/setenv.sh
chown $tomcat_version:$tomcat_version $CATALINA_HOME/bin/setenv.sh
chmod a+x $CATALINA_HOME/bin/setenv.sh
mkdir $CATALINA_BASE/temp
chown $tomcat_version:$tomcat_version $CATALINA_BASE/temp



tomcatinstall=$(dpkg --list | grep $tomcat-version | wc -l)
if [ $tomcatinstall -lt 1 ] ; then
	echo "Could not install $tomcat_version. Aborting."
	exit 1; 
fi

echo "installing $mysql_version..."
debconf-set-selections <<< "$mysql_version mysql-server/root_password password eshh"
debconf-set-selections <<< "$mysql_version mysql-server/root_password_again password eshh"
apt-get -y -qq install $mysql_version > /dev/null

mysqlinstall=$(dpkg --list | grep $mysql_version | wc -l)
if [ $mysqlinstall -lt 1 ] ; then
	echo "Could not install $mysql_version. Aborting."
	exit 1; 
fi

ip=$(hostname -I)
echo "bind-address 	= $ip" > /etc/mysql/conf.d/wakeuplight.cnf

echo "adding test data to database mydb..."
mysql --user=root --password=eshh < $sqlFile

rm -rf $CATALINA_BASE/webapps/$context
rm -rf $CATALINA_BASE/webapps/$context.war
cp $warFile $CATALINA_BASE/webapps/$context.war
chown -R $tomcat_version:$tomcat_version $CATALINA_BASE/webapps


#fuer Treiber benoetigt 
echo "install python-mysqldb...."
apt-get install -y python-mysqldb




systemctl restart $tomcat_version

### summary
echo "All finished!"
echo "MySQL ist available at $ip on port 3306, use the wakeuplight user!"
echo "REST API is available at $ip:8080/rest/"

