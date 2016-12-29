#!/usr/bin/python
# -*- coding: utf-8 -*-
from c_LEDStrip2 import LEDStrip
import time
import random
from configparser import readConfig

configfile ="Config/treiberConfig.txt"
configParam = "treiber-config"
config = readConfig(configfile,configParam)
strip = LEDStrip(config.getConfigParam("anzleds"),config.getConfigParam("striptype"),config.getConfigParam("debugmode"))
anzLeds = int(config.getConfigParam("anzleds"))
# testnr 2 alle LED's können auf grün geschaltet werden
strip.setFullStripColor(0,255,0,1)
strip.printStripChanges()
strip.setColorToStrip()
time.sleep(10)


# testnr 3 alle LED's können auf rot geschaltet werden
strip.setFullStripColor(255,0,0,1)
strip.printStripChanges()
strip.setColorToStrip()
time.sleep(10)

# testnr 4 alle LED's können auf blau geschaltet werden
strip.setFullStripColor(0,0,255,1)
strip.printStripChanges()
strip.setColorToStrip()
time.sleep(10)

# testnr 5 alle LED's können einzeln auf rot geschaltet werden
for i in range(0,anzLeds):
    strip.setFullStripColor(0, 0, 0, 1)
    strip.setOneLEDColor(i,255,0,0,1)
    strip.printStripChanges()
    strip.setColorToStrip()
    time.sleep(0.1)


# testnr 6 alle LED's können einzeln auf grün geschaltet werden
for i in range(0,anzLeds):
    strip.setFullStripColor(0, 0, 0, 1)
    strip.setOneLEDColor(i,0,255,0,1)
    strip.printStripChanges()
    strip.setColorToStrip()
    time.sleep(0.1)

# testnr 7 alle LED's können einzeln auf blau geschaltet werden
for i in range(0,anzLeds):
    strip.setFullStripColor(0, 0, 0, 1)
    strip.setOneLEDColor(i,0,0,255,1)
    strip.printStripChanges()
    strip.setColorToStrip()
    time.sleep(0.1)

# testnr 8 LED's können einzeln angesprochen werden
for i in range(0,anzLeds):
    strip.setFullStripColor(0, 0, 0, 1)
    # rot
    for h in range (0,256):
        strip.setOneLEDColor(i, h, 0, 0, 1)
        strip.printStripChanges()
        strip.setColorToStrip()
    for h in range(256,0,-1):
        strip.setOneLEDColor(i, h, 0, 0, 1)
        strip.printStripChanges()
        strip.setColorToStrip()
    #grün
    for h in range(0, 256):
        strip.setOneLEDColor(i, 0, h, 0, 1)
        strip.printStripChanges()
        strip.setColorToStrip()
    for h in range(256, 0, -1):
        strip.setOneLEDColor(i, 0, h, 0, 1)
        strip.printStripChanges()
        strip.setColorToStrip()
    #blau
    for h in range(0, 256):
        strip.setOneLEDColor(i, 0, 0, h, 1)
        strip.printStripChanges()
        strip.setColorToStrip()
    for h in range(256, 0, -1):
        strip.setOneLEDColor(i, 0, 0, h, 1)
        strip.printStripChanges()
        strip.setColorToStrip()