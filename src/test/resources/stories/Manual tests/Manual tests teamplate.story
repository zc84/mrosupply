Scenario: Email us function
Meta:
@tag priority:p1,type:manual

Given open home page
When click on Email Us link
And Fill all details
And enter captcha
And click Submit
Then see 'Thank you for contacting us. Your message is important to us. We will be contacting you as soon as possible to handle it.' message

Scenario: Chat with sales function
Meta:
@tag priority:p1,type:manual

Given open home page
When click on  Chat with sales
Then chat pop up appears
When enter message
And click Send
Then see 'Thanks for your message! We'll get back to you shortly.' message