Meta:
@capability registration

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
