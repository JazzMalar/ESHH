from c_api import callApi
from c_alarm import alarm
from c_timerAktiv import timerAktiv
import sys, datetime, time
from c_actionGroupMember import actionGroupMembers

sys.path.append('../')
from configparser import readConfig

configfile ="../Config/treiberConfig.txt"
configParam = "api-config"
treiberConfigParam = "treiber-config"
config = readConfig(configfile,configParam)
treiberConfig = readConfig(configfile,treiberConfigParam)
pir = False


apiURL =  config.getConfigParam("api")
aktiveNightlight = []
def bewegungssensor(pin):
    global pir
    if treiberConfig.getConfigParam("debugmode").lower() == "false":
        if(GPIO.input(pir)):
            activateNightlight = callApi(apiURL, "nightlight/activate?StringID=WS2801_01")
            actionGroups = actionGroupMembers(apiURL,"nightlight",True)
            # wird benoetigt, damit tomcat reagieren kann!!
            time.sleep(0.1)
            for i in actionGroups.getAGPArr():
                aktiveNightlight.append(timerAktiv(apiURL,i.getFromActionGroupMember('groupId'),"nightlight",i.getFromActionGroupMemberMEMBERS('offset')))
        elif GPIO.input(pir) == False:
            actionGroups = actionGroupMembers(apiURL, "nightlight/disable?StringID=WS2801_01")
            for i in aktiveNightlight:
                i.disableAlarm()



if treiberConfig.getConfigParam("debugmode").lower() == "false":
    import RPi.GPIO as GPIO
    GPIO.setmode(GPIO.BOARD)
    global pir
    pir = int(treiberConfig.getConfigParam("pir"))
    GPIO.setup(pir, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
    GPIO.add_event_detect(pir, GPIO.BOTH, callback=bewegungssensor)

#bewegungssensor(1)

alarmObj = []
aktiveTimer = []
def updateAlarms():
    global alarmObj,aktiveTimer
    alarms = callApi(apiURL, "alarms")
    for al in alarms.checkAlarms(alarms.getArray()):
        vorhanden = False
        for alObj in alarmObj:
            if int(al["alarmId"])== alObj.getIdAlarm():
                vorhanden = True
                #update, falls Zeit etc geaendert wird!!
                alObj.updateAlarm(al["startTime"], al["repeatPattern"], al["alarmId"], al["enabled"], al["actionGroup"],al["offset"])
                break
        if not vorhanden:
            alarmObj.append(alarm(al["startTime"], al["repeatPattern"], al["alarmId"], al["enabled"], al["actionGroup"],al["offset"]))



try:
    while True:
        updateAlarms()
        for al in alarmObj:
            if al.getIsActive():
                if al.getIsWeekdayTrue(int(datetime.datetime.today().weekday())):
                    print "Timer aktiv, Wochentag aktiv"
                    if(int(al.getMinsToWait()) == 0):
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
        print "sleep: " +str(nextmin)+"sec"
        time.sleep(nextmin)
except KeyboardInterrupt:
    if treiberConfig.getConfigParam("debugmode").lower() == "false":
        GPIO.cleanup()


