Scenario: Forgot password functionality
Meta:
@tag priority:p1

Given user on the home page
When click 'Sign In' link
Then user on the 'LoginPage'
When click on 'Forgot password link'
And enter 'test@test.com' in 'Reset email field'
And click on 'Reset button'
Then see 'Email with further instructions about password reset has been sent' message
And see 'Please check your email inbox' message
