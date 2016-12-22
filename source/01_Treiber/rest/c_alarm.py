import datetime


class alarm:
    def __init__(self,startTime,repeatPattern,idTask,active,idActivationGroup):
        self.updateAlarm(startTime,repeatPattern,idTask,active,idActivationGroup)

    def getIsActive(self):
        return self.active

    def updateAlarm(self,startTime,repeatPattern,idTask,active,idActivationGroup):
        self.startTime = startTime
        self.repeatPattern = repeatPattern
        self.idTask = int(idTask)
        self.active = bool(active)
        self.idActivationGroup = idActivationGroup

        #updateFkts
        self.updateRepeatPattern(self.repeatPattern)

    def getIdTask(self):
        return self.idTask

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
        print calculatedTime
        return calculatedTime

