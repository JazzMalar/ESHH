from c_actionGroupMember import actionGroupMembers
from c_deviceActions import deviceActions
from c_deviceStrip  import deviceWS2801
import uuid, datetime,random

class timerAktiv:

    class color:
        def __init__(self,color):
            if len(color) == 1:
                #alle die gleiche Farbe
                self.r = int(color)
                self.g = int(color)
                self.b = int(color)
            elif len(color) == 3:
                self.r = int(color[0])
                self.g = int(color[1])
                self.b = int(color[2])
            elif len(color) == 6:
                self.r = int(color[0:2])
                self.g = int(color[2:4])
                self.b = int(color[4:6])
            elif len(color) == 9:
                self.r = int(color[0:3])
                self.g = int(color[3:6])
                self.b = int(color[6:9])
            else:
                print "laenge color nicht gefunden!!! laenge: "+str(len(color))


        def setColor(self,color,value):
            if color == 'r':
                self.r = value
            elif color == 'g':
                self.g = value
            elif color == 'b':
                self.b = value

        def getColor(self,color):
            if color == 'r':
                return self.r
            elif color == 'g':
                return self.g
            elif color == 'b':
                return self.b

    def __init__(self,apiUrl,actionGroup,alarmId,offset):
        #Todo, random entfernen
        self.prio       = random.randint(1, 100)#10
        self.uuid       = uuid.uuid4()
        print "timerInit "+str(self.uuid)
        self.activ      = True
        self.apiUrl     = apiUrl
        self.actionGroup = actionGroup
        self.alarmId    = alarmId
        self.offset     = offset
        # wie lange der Timer dauert, 1 step = 1 min
        self.maxStep  = 30
        self.actionGroupMemberApi = "actiongroupmembers?GroupID="+str(self.actionGroup)
        self.AGMObj     = actionGroupMembers(apiUrl,self.actionGroupMemberApi)
        for i in self.AGMObj.getAGPArr():
            #ws2801
            if int(i.getFromActionGroupMember('deviceId')) == 1:
                actionId = i.getFromActionGroupMember('actionId')
                self.deviceActionsObj = deviceActions(self.apiUrl,'deviceactions?StringID=WS2801_01&ID='+actionId)
                self.colorStart     = timerAktiv.color(self.deviceActionsObj.getFieldValue("ColorStart"))
                self.colorNow       = timerAktiv.color(self.deviceActionsObj.getFieldValue("ColorStart"))
                self.colorEnd       = timerAktiv.color(self.deviceActionsObj.getFieldValue("ColorEnd"))
                self.anzLed         = self.deviceActionsObj.getFieldValue("NumLeds")
                break
        #Todo hier evt. anzahl LEDs uebergeben!
        self.strip = deviceWS2801().getStrip()
        self.setStrip(self.colorStart)
        self.nextStep = 2 # erster schritt schon durch
        self.oldTime = datetime.datetime.now()

    def setStrip(self,color):
        for i in range(0, int(self.anzLed)):
            self.strip.setOneLEDColor(i, color.getColor('r'), color.getColor('g'),
                                      color.getColor('b'), self.prio, self.uuid)
    def writeStrip(self):
        self.strip.setColorToStrip()
        self.strip.printStrip()

    def getActiv(self):
        return self.activ

    # pro min 1 step
    def __calculateColor(self,step):
        if step == 0:
            step = 1
        colors = ['r','g','b']
        for color in colors:
            if (self.colorStart.getColor(color) < self.colorEnd.getColor(color)):
                calculate = (self.colorEnd.getColor(color) - self.colorStart.getColor(color))/self.maxStep
                self.colorNow.setColor(color,calculate)

    def run(self):
        print "timer run uuid: "+str(self.uuid)
        now = datetime.datetime.now()
        timecalc = (now-self.oldTime)
        min = (timecalc.seconds/60)%60
        if min >=1:
            if (self.nextStep < self.maxStep):
                self.__calculateColor(self.nextStep)
                self.setStrip(self.colorNow)
                self.nextStep = self.nextStep + 1
            else:
                self.strip.removeLEDUsedByFullStrip(self.uuid)
                self.activ = False


