Feature: fullfill student form if the student is authorized

@important
Scenario Outline: fill the student form and submit
	Given navitate to login "<url>"
	And submit "<username>" and "<password>"
	Then user is logged in
	Given user fill the form
	When user submit
	Then pop up appear with successfull

Examples: 
	|					url					 |   username   |    password	  |
	|	http://localhost:4200/login 		 |	 zelkotb	|  mamamangepapa  |