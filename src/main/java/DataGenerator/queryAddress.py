from asyncio.windows_events import NULL
from faker import Faker
import random
faker = Faker('en_CA')

queryAddress = ""
queryEmployee = ""




for addressID in range(4,151):
    province = faker.province()
    city = faker.city()
    street = faker.street_address()
    postalcode = faker.postalcode()
    queryAddress += "(" + str(addressID)+",'" +street + "','" + city + "','" + province + "','" + postalcode + "'),\n"

    
for employeeID in range(0,40):
    queryEmployee += "("+str(employeeID)+","+str(40+employeeID)+",'"+ str(random.randint(100,999))+" " +str(random.randint(100,999))+ " "+ str(random.randint(100,999))+"'),\n"

file1 = open("queryEmployee.txt", "w")
file1.write(queryEmployee)
file1.close()
