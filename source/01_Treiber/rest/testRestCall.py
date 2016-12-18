
import requests
from xml.dom.minidom import parse, parseString


resp = requests.get('http://localhost:8080/api/alarms')
if resp.status_code != 200:
    # This means something went wrong.
    raise ApiError('GET /tasks/ {}'.format(resp.status_code))

dom = parseString(resp.content)


def readOut(dom):
    if dom.hasChildNodes:
        #checken ob nur noch textnode ist
        if (len(dom.childNodes)==1):
            #print "    Textnode"
            #print "    "+dom.tagName
            #print "    "+dom.firstChild.nodeValue
            #dict
            dict={}
            dict[str(dom.tagName)]=str(dom.firstChild.nodeValue)
            return dict
        else:
            bigdict={}
            for child in dom.childNodes:
                # try:
                #     print child.tagName
                # except:
                #     print "except"

                dict=readOut(child)
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


#print "********************************"
#readOut(dom.documentElement)

arr=[]

for i in dom.documentElement.childNodes:
    arr.append(readOut(i))

print "####################################"
print dom.documentElement.tagName
print arr

