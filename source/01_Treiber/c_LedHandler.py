from c_LEDObject import LEDObject

class LEDHandler():
    def __init__(self):
        self.leds = []
        # InitLed, damit immer eins verfuegbar ist
        led = LEDObject()
        led.setRGB(0,0,0,0,self)
        self.leds.append(led)



    # gibt immer LED mit hoechster Prioritaet zurueck
    def __getLED(self):
        if self.leds:
            highestPrioObj=-1
            highestPrio=-1
            for x in self.leds:
                if(x.getPrio() > highestPrio):
                    highestPrioObj=x
                    highestPrio = x.getPrio()
            # TODO prio kann auch 0 sein
            #if(highestPrioObj):
            return highestPrioObj
            #else:
            #    return False
        else:
            return false

    def getPrio(self):
        return self.__getLED().getPrio()

    def getUsedBy(self):
        return self.__getLED().getUsedBy()

    def setRGB(self,r,g,b,prio,usedBy):
        vorhanden = False #usedBy kann verschieden sein
        for x in self.leds:
            if x.getUsedBy == usedBy:
                vorhanden = True
                x.setRGB(r,g,b,prio,usedBy)
                continue
        if not vorhanden:
            led = LEDObject()
            led.setRGB(r,g,b,prio,usedBy)
            self.leds.append(led)

    def removeLed(self,usedBy):
        found = False
        for x in self.leds:
            if x.getUsedBy() == usedBy:
                self.leds.remove(x)
                found = True
        return found

    def getR(self):
        return self.__getLED().getR()

    def getG(self):
        return self.__getLED().getG()

    def getB(self):
        return self.__getLED().getB()


