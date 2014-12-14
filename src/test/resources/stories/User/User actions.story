Scenario: User should be able to register
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

Scenario: User should be able to subscribe for weekly updates
Meta:
@tag priority:p3
@issue 309

Given user on the home page
When enter random email in 'Submit email field'
And click on 'Submit email button'
Then 'Subscribe bunner appeared' available

Scenario: Add/Delete credit card
Meta:
@tag priority:p4,type:manual
@issue 305

Given logged user on the home page
When click on 'My account link'
Then user on the 'AccountPage'
When click on 'Payment Info'
And click on 'New button'
Then 'Add credit card popup' available
When add credit card

