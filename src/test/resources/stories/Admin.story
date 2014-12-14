Scenario: Change order status to Complete
Meta:
@tag priority:p1

Given admin on the home page
When click on 'Orders link'
And open 'Unclaimed' order created by 'automation user'
And select 'Completed --' from 'Status dropdown'
And click on 'Save button'
Then status is 'Completed' for this order
And email 'Order Status - Completed' recieved after '5' mins
And email contains text 'Your Order #'
And email contains text 'This is a confirmation of your order'
And email contains text 'The current status of your order has been changed to "Completed'
