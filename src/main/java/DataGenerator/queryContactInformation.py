from faker import Faker
import random
faker = Faker('en_CA')

queryAddress = ""
queryContactInfo = ""
queryCustomer = ""


contactinfoID = 3
for customerID in range(1,151):
    contactinfoID = contactinfoID +1
    email = faker.email()
    phone = faker.phone_number()
    queryCustomer += "(" + str(contactinfoID) + ",'" + email + "','" + phone + "'),\n"
    


file1 = open("queryContactInformation.txt", "w")
file1.write(queryCustomer)
file1.close()
