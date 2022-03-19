import random
#class
#instance variable -> what it knows
#constructor ( type of method ) 
#methods -> what it can done


#bank
class Bank:
	def __init__(self, accounts = {}):
		self.accounts = accounts
		
	#methods
	def addAccount(self, account):
		acctNum = random.randrange(1, 9999999)
		while acctNum in self.accounts:
			acctNum = random.randrange(1, 9999999)
		
		self.accounts[acctNum] = account
		return acctNum
	
	def __str__(self):
		s = ""
		for i in self.accounts:
			s += f'Account #{i}: {self.accounts[i]}\n'
		
		return s
	
	def transfer(self, fromAccnt, toAccnt, amount):
		if fromAccnt.withdraw(amount) > 0:
			toAccnt.deposit(amount)
		
		
#bank account
class Account:
	#instance variables:
	#Balance
	#Owner
	#Security
	#Interest Rate
	
	#methods:
	#Deposit
	#Withdraw
	
	def __init__(self, balance = 0, security = '', owner = '', rate = 0):
		self.balance = balance
		self.security = hash(security)
		self.owner = owner
		self.rate = rate

	def printBirthday(self):
		print(self.birthday)
	
	def __str__(self):
		return f'{self.owner}\'s account has ${self.balance}.'
	
	def checkSecurity(self, sec = ''):
		return self.security == hash(sec)
	
	def withdraw(self, amount, sec = ''):
		if self.checkSecurity(sec):
			if 0 < amount <= self.balance:
				self.balance -= amount
			
			return self.balance
		else:
			print("Invalid security code. Access denied.")
			return -1
	
	def deposit(self, amount):
		if amount > 0:
			self.balance += amount
		return self.balance

a = Account(1000, owner = 'George Lopez')
b = Account(1500, owner = 'Billy Ray Cyrus', rate = 10)
c = Account(2300, owner = 'Gabe Newell')
a.deposit(100)
print(a)

ozk = Bank()
accountA = ozk.addAccount(a)
accountB = ozk.addAccount(b)
accountC = ozk.addAccount(c)

print(ozk)

ozk.transfer(a, b, 500)
print(ozk)


