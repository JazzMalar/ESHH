import datetime


class alarm:
    def __init__(self, startTime, repeatPattern, alarmId, active, idActivationGroup, offset):
        self.updateAlarm(startTime, repeatPattern, alarmId, active, idActivationGroup, offset)

    def getIsActive(self):
        return self.active

    def updateAlarm(self,startTime,repeatPattern,alarmId,active,idActivationGroup,offset):
        self.startTime = startTime[-9:-4] # Nur Zeit aus startTime auslesen 1970-01-01T08:00:00Z
        self.repeatPattern = repeatPattern
        self.alarmId = int(alarmId)
        self.active = bool(active)
        self.idActivationGroup = idActivationGroup
        self.offset = offset

        #updateFkts
        self.updateRepeatPattern(self.repeatPattern)

    def getIdAlarm(self):
        return self.alarmId

    def getOffset(self):
        return self.offset

    def getIdActivationGroup(self):
        return self.idActivationGroup

   # def updateIsActive(self,active):
        # if active == "true":
        #     return True

    def updateRepeatPattern(self,repeatPattern):
        self.repeatPatternArr = []
        listRepeatPattern = list(repeatPattern)
        for day in listRepeatPattern:
            # tage in ein Array ueberfuehren
            if(day =="1"):
                self.repeatPatternArr.append(True)
            else:
                self.repeatPatternArr.append( False)

    def getTimeArr(self,startTime):
        print startTime
        startTimeArr = []
        # arr[0] = Stunden, arr[1]=Minuten
        timeSplit = startTime.split(':')
        for i in timeSplit:
            startTimeArr.append(i)
        return startTimeArr


    def getNow(self):
        return datetime.datetime.now().strftime("%H:%M")

    def calculateMins(self,time):
        timeArr = self.getTimeArr(time)
        return int(timeArr[0])*60+int(timeArr[1])


    def getMinsToWait(self):
        now = self.calculateMins(self.getNow())
        startTime = self.calculateMins(self.startTime)
        calculatedTime = startTime - now
        print "Zeit zu warten bis timer startet: "+str(calculatedTime)
        return calculatedTime

    def getIsWeekdayTrue(self,weekday):
        # 0 = Montag
        if (weekday < len(self.repeatPatternArr)):
            return self.repeatPatternArr[weekday]
        else:
            #besser nicht einschalten
            return False
