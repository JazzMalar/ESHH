import sys
sys.path.append('../')
import utilitis
import xmltodict


class actionGroupMember:
    def __init__(self, dict):
        self.dict = dict

    def getFromActionGroupMember(self, name):
        ret = str(self.dict[name])
        print ret
        return ret

    def getFromActionGroupMemberMEMBERS(self, name):
        ret = str(self.dict["members"][name])
        print ret
        return ret


class actionGroupMembers:
    def __init__(self,apiUrl,apiName,nightlight = False):
        self.nightLight = nightlight
        self.apiUrl = apiUrl
        self.apiName = apiName
        self.AGM = []
        xml = utilitis.readXml(self.apiUrl, self.apiName)
        if xml == False:
            return
        fulldict = xmltodict.parse(xml)
        #print fulldict
        #print str(fulldict)
        # print str(fulldict).count('actionGroupMember')/2
        #print fulldict['actionGroupMembers']['actionGroupMember'][0]
        if not nightlight:
            count = int(str(fulldict).count('actionGroupMember'))/2
            if count > 1:
                for i in range(0,int(str(fulldict).count('actionGroupMember'))/2):
                    self.AGM.append(actionGroupMember(fulldict['actionGroupMembers']['actionGroupMember'][i]))
            else:
                self.AGM.append(actionGroupMember(fulldict['actionGroupMembers']['actionGroupMember']))
           # print fulldict['actionGroupMembers']['actionGroupMember'][i]
        else:
            #print int(str(fulldict).count("'actionGroup'"))
            for i in range(0,int(str(fulldict).count("'actionGroup'"))):
                #print fulldict['actionGroups']['actionGroup'][i]
                self.AGM.append(actionGroupMember(fulldict['actionGroups']['actionGroup'][i]))

       # for i in self.AGP:
       #     print i.getFromActionGroupMember('offset')

    def getAGPArr(self):
        return self.AGM

#a = actionGroupMembers('http://178.83.203.182:8080/api','actiongroupmembers?GroupID=1')