#!/usr/bin/python
import requests
from xml.dom.minidom import parse, parseString


class callApi:
    def __init__(self,apiUrl,apiName):
        self.apiUrl = apiUrl
        self.apiName = apiName
        self.saveDatas = []
        self.getDatas()


    def getDatas(self):
        try:
            print "request "+str(self.apiUrl+"/"+self.apiName)
            resp = requests.get(self.apiUrl+"/"+self.apiName) # 'http://localhost:8080/api/alarms'
            if resp.status_code != 200:
                print "requesturl: "+str(self.apiUrl+"/"+self.apiName)+" status_code:"+str(resp.status_code)
                # This means something went wrong.
                #raise ApiError('GET /tasks/ {}'.format(resp.status_code))
            dom = parseString(resp.content)
            for i in dom.documentElement.childNodes:
                self.saveDatas.append(self.readOut(i))
        except:
            print "tomcat nicht gestartet??"
            return False


    def getArray(self):
        return self.saveDatas

    def readOut(self,dom):
        if dom.hasChildNodes:
            # checken ob nur noch textnode ist
            if (len(dom.childNodes) == 1):
                dict = {}
                dict[str(dom.tagName)] = str(dom.firstChild.nodeValue)
                return dict
            else:
                bigdict = {}
                for child in dom.childNodes:
                    dict = self.readOut(child)
                    if bool(dict):
                        #print dict
                        bigdict.update(dict)
                        #print bigdict
                return bigdict
        else:
            try:
                print dom.tagName
                print dom.nodeValue
            except:
                print "except"



    def checkAlarms(self,arr):
        for dict in arr:
            if "startTime" not in dict:
                print "start not in dict"
                dict["startTime"]="21:03"

        return arr