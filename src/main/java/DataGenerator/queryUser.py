from asyncio.windows_events import NULL
from faker import Faker
import random
faker = Faker('en_CA')

queryAddress = ""
queryCustomer = ""

userID = 2
addressID = 2
contactinfoID = 2

for customerID in range(1,151):
    contactinfoID+=1
    addressID+=1
    userID+=1
    
    ssin = faker.ssn().replace(" ","")
    firstName = faker.first_name()
    middleName = faker.first_name()
    lastName = faker.last_name()
    if customerID %2 ==0:   
        gender = "Male"
    else:
        gender = "Female"
    year = str(random.randint(1980,2014))
    
    month = random.randint(1,12)
    if month<10:
        strmonth= "0"+str(month)
    else:
        strmonth= str(month)
    day = str(random.randint(1,30))
    date = year+"-"+strmonth+"-"+day
    queryCustomer += "('" + firstName + "','" + middleName + "','" + lastName + "','" + gender+"', " + str(ssin) + ",'"+ date+"',"+ str(contactinfoID) + "," + str(userID)+","+str(addressID)+"),\n"
    


file1 = open("queryUser.txt", "w")
file1.write(queryCustomer)
file1.close()
