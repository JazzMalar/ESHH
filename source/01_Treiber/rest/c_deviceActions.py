import xmltodict
import sys
sys.path.append('../')
import utilitis


class fields:
    def __init__(self,fieldName,fieldValue):
        self.fieldName = str(fieldName)
        self.fieldValue = str(fieldValue)

    def getFieldName(self):
        return self.fieldName

    def getFieldValue(self):
        return self.fieldValue


class deviceActions:
    def __init__(self,apiUrl,apiName):
        self.apiUrl = apiUrl
        self.apiName = apiName
        self.fields = []
        self.xml = utilitis.readXml(self.apiUrl, self.apiName)

        if self.xml != False:
            fulldict = xmltodict.parse(self.xml)
            for i in range(0,int(fulldict['deviceActions']['deviceAction']['n'])):
                dict = fulldict['deviceActions']['deviceAction']['fields'][i]
                self.fields.append(fields(dict['fieldName'],dict['fieldValue']))

            for i in self.fields:
                print "Name: "+i.getFieldName() +"; Value: "+i.getFieldValue()
            self.deviceActionId = fulldict['deviceActions']['deviceAction']['deviceActionId']

    def getFieldValue(self,fieldName):
        for i in self.fields:
            if i.getFieldName().lower() == fieldName.lower():
                return i.getFieldValue()

        return False




#a = deviceActions('http://178.83.203.182:8080/api','deviceactions?StringID=WS2801_01&ID=1')