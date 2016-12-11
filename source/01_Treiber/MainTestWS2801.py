#!/usr/bin/python
from c_LEDStrip2 import LEDStrip
import time
import random

anzLEDs = 50

strip = LEDStrip(anzLEDs)
strip.getFullStrip()
#ganzen Strip mit blau setzen
strip.setFullStripColor(0,0,255,1)

for i in range (0,anzLEDs):
    # randomFarbe fuer jedes LED, hoehere Prio als blau!
    strip.setOneLEDColor(i,random.randint(0,255),random.randint(0,255),random.randint(0,255),2,2)


for i in range (0,anzLEDs):
    strip.setOneLEDColor(i, 255,255,255, 151, 100)

strip.setColorToStrip()
strip.printStrip()
time.sleep(5)


for i in range (0,anzLEDs,3):
    strip.setOneLEDColor(i, 255, 0, 0, 177, 1234567)
    strip.setOneLEDColor(i+1, 0, 255, 0, 177, 1234567)
    strip.setOneLEDColor(i+2, 0, 0, 255, 177, 1234567)

strip.setColorToStrip()
strip.printStrip()
time.sleep(10)

i = 1234567
print "anzahl removed leds: "+str(strip.removeLEDUsedByFullStrip(i)) +" bei usedby: "+str(i)
strip.setColorToStrip()
strip.printStrip()
time.sleep(10)

i = 100
print "anzahl removed leds: "+str(strip.removeLEDUsedByFullStrip(i)) +" bei usedby: "+str(i)
strip.setColorToStrip()
strip.printStrip()
time.sleep(10)

i = 2
print "anzahl removed leds: "+str(strip.removeLEDUsedByFullStrip(i)) +" bei usedby: "+str(i)
strip.setColorToStrip()
strip.printStrip()
time.sleep(10)
i = 1
print "anzahl removed leds: "+str(strip.removeLEDUsedByFullStrip(i)) +" bei usedby: "+str(i)
strip.setColorToStrip()
strip.printStrip()
