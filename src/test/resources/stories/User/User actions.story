Meta:
@capability user actions

Scenario: 01. User should be able to subscribe for weekly updates
Meta:
@tag priority:p3
@issue 309

Given user on the home page
When enter random email in 'Submit email field'
And click on 'Submit email button'
Then 'Subscribe bunner appeared' available


Scenario: 02. User should be able to logout
Meta:
@priority p3

Given logged user on the home page
When click logout
Then 'Sign In' available

Scenario: 03. Contact Us
Meta:
@tag priority:p2

Given logged user on the home page
When click 'Contact Us' link
Then see 'Call us at (888) 671-2883' message
And see 'customerservice@mrosupply.com' message
And see 'Send us a message' message
