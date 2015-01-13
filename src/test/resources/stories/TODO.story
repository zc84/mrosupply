Scenario: todo
Meta:
@tag priority:p4,type:manual
@issue 305

Given todo

Scenario: My Account/Company Accounts: User should be able to invite another user
-	Given logged user on the home page
-	When click ‘My Account’ link
-	When click ‘Company Accounts’ link
-	Then see ‘Company Accounts’ message
-	And fill ‘Invite Another User’ field
-	And submit ‘Invite ’ button
-	Then see ’Email with invite to ~email~ has been sent successfully’ message

Scenario: 08. Minimum Order Quantity
Meta:
@tag priority:p3
@ignore

Given user on the home page
When click 'Brands' link
Then user on the 'ProductsPage'
When click 'ACME Electric' link
And open '885230' item
Then manufacturer minimum quantity set
When click on 'Add to basket button'
Then user on the 'ShoppingCartPage'
