from c_LEDStrip2 import LEDStrip
import time
strip = LEDStrip(50,"LPD6803",False)
strip.getFullStrip()
strip.setFullStripColor(0,0,255,1)
strip.setOneLEDColor(5,1,1,233,2)

strip.setColorToStrip()
strip.printStrip()
time.sleep(1)

while True:
    for i in range(0,255,10):
        #time.sleep(1)
        strip.setFullStripColor(i, 0,0 , 10)
        strip.printStrip()
        strip.setColorToStrip()

    for i in range (255,0,10):
        strip.setFullStripColor(i, 0, 0, 10)
        strip.printStrip()
        strip.setColorToStrip()
