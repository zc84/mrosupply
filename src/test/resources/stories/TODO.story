Scenario: todo
Meta:
@tag priority:p4,type:manual
@issue 305

Given todo

Scenario: Test should open all submenu from My account to not have broken pages
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
