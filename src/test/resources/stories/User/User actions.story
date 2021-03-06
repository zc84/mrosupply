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


Scenario: 03. User should be able to logout
Meta:
@priority p3

Given logged user on the home page
When click logout
Then 'Sign In' available

Scenario: 04. Contact Us
Meta:
@tag priority:p2

Given logged user on the home page
When click 'Contact Us' link
Then see 'Call us at (888) 671-2883' message
And see 'customerservice@mrosupply.com' message
And see 'Send us a message' message
