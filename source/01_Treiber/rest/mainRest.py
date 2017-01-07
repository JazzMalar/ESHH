from c_api import callApi
from c_alarm import alarm
from c_timerAktiv import timerAktiv
import sys, datetime, time

sys.path.append('../')
from configparser import readConfig

configfile ="../Config/treiberConfig.txt"
configParam = "api-config"
config = readConfig(configfile,configParam)

apiURL =  config.getConfigParam("api")

alarms = callApi(apiURL,"alarms")
print alarms.getArray()
#print alarms.checkAlarms(alarms.getArray())
alarmObj = []
aktiveTimer = []
for al in alarms.checkAlarms(alarms.getArray()):
    print al['startTime']
    alarmObj.append(alarm(al["startTime"],al["repeatPattern"],al["alarmId"],al["enabled"],al["actionGroup"],al["offset"]))

while True:
    for al in alarmObj:
        if al.getIsActive():
            if al.getIsWeekdayTrue(int(datetime.datetime.today().weekday())):
                print "Timer aktiv, Wochentag aktiv"
                #if(int(al.getMinsToWait()) == 0):
                print "timer starten"
                # timer wird nur einmal gestartet, da in der gleichen minute nur einmal das aufgerufen wird!
                aktiveTimer.append(timerAktiv(apiURL,al.getIdActivationGroup(),al.getIdAlarm(),al.getOffset()))
            else :
                print "Timer aktiv, Wochentag inaktiv"
        else:
            print "Timer inaktiv\n"


    for timer in aktiveTimer:
        if timer.getActiv():
            timer.run()
            if timer == aktiveTimer[-1]:
                timer.writeStrip()
        else:
            if timer == aktiveTimer[-1]:
                timer.writeStrip()
            #timer wird nicht mehr gebraucht
            aktiveTimer.remove(timer)
    #warten bis naechste min beginnt
    nextmin = 60-(int(datetime.datetime.now().second))
    print nextmin
    time.sleep(nextmin)

