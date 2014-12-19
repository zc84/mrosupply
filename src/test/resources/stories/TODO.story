1. Scenario: Add/Delete credit card
Meta:
@tag priority:p4,type:manual
@issue 305

Given open home page
When login
And go to My account
And click on 'Payment Info link'

2. Scenario: User should be able to add on cart a certain amount of qty for any product on cart
-	Given logged user on the home page 
-	When click ‘Brands’ link
-	Then user on the ‘Brand Page’
-	When click ‘ACME Electric’ link
-	Add at any product specific quantity and add to cart from list http://screencast.com/t/KYeCOEJ59CY
-	Open cart 
-	Verify that product has been added to cart with that specific quantity

3. Scenario: User should be able to sign out successfully 
-	Given logged user on the home page
-	Click Sign out and read ‘Sign in’ button to verify

4. Scenario: Test should open all submenu from My account to not have broken pages
-	Given logged user on My Account page 
-	Then see ‘Account Center’ message
-	Then see ‘View All Quotes’ message
-	Then see ‘View All Orders’ message
-	Then see ‘View All Recently Viewed Products’ message
-	When click ‘My Chats’ link
-	Then see ‘Recent Chats’ message
-	When click ‘Forms’ link
-	Then see ‘Attached Forms/Documents’ message
-	When click ‘Payment Info’ link
-	Then see ‘Credit Cards’ message
-	When click ‘Settings’ link
-	Then see ‘User Login and Password’ message
-	Then see ‘User Profile’ message
-	Then see ‘Deactivate Account’ message
-	When click ‘Company Accounts’ link
-	Then see ‘Company Accounts’ message
-	Then see ‘Invite Another User’ message
-	When click ‘Favorite’ link
-	Then see ‘Favorite products’ message
-	When click ‘Recently Viewed’ link
-	Then see ‘Recently viewed products’ message
-	When click ‘Order History’ link 
-	Then see ‘Order History – Company name’ message
-	When click ‘Quote History’ link
-	Then see ‘Quotes History’ message
-	Then see ‘Open Quotes’ message
-	Then see ‘Expired Quotes’ message

5. Scenario: My Account/Company Accounts: User should be able to invite another user
-	Given logged user on the home page
-	When click ‘My Account’ link
-	When click ‘Company Accounts’ link
-	Then see ‘Company Accounts’ message
-	And fill ‘Invite Another User’ field
-	And submit ‘Invite ’ button
-	Then see ’Email with invite to ~email~ has been sent successfully’ message

6. Scenario: My Account/Forms: User should be able to upload form
-	Given logged user on the home page
-	When click ‘My Account’ link
-	When click ‘Forms’  link
-	Then see ‘Attached Forms/Documents’ message
-	And upload document 
-	And fill document name field 
-	And submit
-	Then see ‘File has been uploaded successfully’ message

