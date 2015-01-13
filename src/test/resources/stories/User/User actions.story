Meta:
@capability user actions

Scenario: 01. User should be able to register
Meta:
@tag priority:p1
@issue 294

Given user on the home page
When click 'Register' link
Then user on the 'RegistrationPage'
When fill first registration form
And click 'Next >>>' button
When fill second registration form
And click 'Next >>>' button
And click 'Register' button
Then see 'Account Center' message

Scenario: 02. User should be able to subscribe for weekly updates
Meta:
@tag priority:p3
@issue 309

Given user on the home page
When enter random email in 'Submit email field'
And click on 'Submit email button'
Then 'Subscribe bunner appeared' available

Scenario: 03. Add/Delete credit card
Meta:
@tag priority:p4
@issue 305

Given logged user on the home page
When click on 'My account link'
Then user on the 'AccountPage'
When click 'Payment Info' link
And delete all creadit cards
And click on 'New button'
Then 'Add credit card popup' available
When add credit card
Then see '4242' message

Scenario: 04. Contact Us
Meta:
@tag priority:p2

Given logged user on the home page
When click 'Contact Us' link
Then see 'Call us at (888) 671-2883' message
And see 'customerservice@mrosupply.com' message
And see 'Send us a message' message

Scenario: 05. User should be able to logout
Meta:
@priority p3

Given logged user on the home page
When click logout
Then 'Sign In' available

Scenario: 06. User should be able to upload form
Meta:
@priority p3

Given logged user on the home page
When click on 'My account link'
Then user on the 'AccountPage'
When click 'Forms' link
And click on 'Choose file button'
And select file to upload
And enter 'test upload file' in 'File name field'
And click on 'Submit button'
Then see 'File has been uploaded successfully' message

Scenario: 07. All submenues of My account should be accessible
Meta:
@priority p3

Given logged user on the home page
When click on 'My account link'
Then user on the 'AccountPage'
And see 'Account Center' message
And see 'View All Quotes' message
And see 'View All Orders' message
When click 'My Chats' link
Then see 'Recent Chats' message
When click 'Forms' link
Then see 'Attached Forms/Documents' message
When click 'Payment Info' link
Then see 'Payment Info' message
And see 'Credit Cards' message
When click 'Settings' link
Then see 'User Login and Password' message
And see 'User Profile' message
And see 'Deactivate Account' message
When click 'Favorite' link
Then see 'Favorite products' message
When click 'Recently Viewed' link
Then see 'Recently viewed products' message
When click 'Order History' link
Then see 'Order History' message
When click 'Quote History' link
Then see 'Quotes History' message
Then see 'Open Quotes' message
Then see 'Expired Quotes' message
