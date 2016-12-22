from c_api import callApi
from c_alarm import alarm
import sys

sys.path.append('../')
from configparser import readConfig

configfile ="../Config/treiberConfig.txt"
configParam = "api-config"
config = readConfig(configfile,configParam)

apiURL =  config.getConfigParam("api")

alarms = callApi(apiURL,"alarms")
print alarms.getArray()
print alarms.checkAlarms(alarms.getArray())
alarmObj = []
for al in alarms.checkAlarms(alarms.getArray()):
    print al['startTime']
    alarmObj.append(alarm(al["startTime"],al["repeatPattern"],al["alarmId"],al["enabled"],al["actionGroup"]))

for al in alarmObj:
    if al.getIsActive():
        print "active: \n"
        if(int(al.getMinsToWait()) == 0):
            print "timer starten"

    else:
        print "inactive: \n"

