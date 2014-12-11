Scenario: Complete order
Meta:
@tag priority:p1

Given admin on the home page
When click 'Orders' link
And open 'Unclimed' order created by 'automation user'
And select 'Completed --' from 'Status dropdown'
And click on 'Save button'
Then status is 'Comleted' for this order
And email 'Order Status - Completed' recieved after '2' mins
And email contains text 'Your Order #'
And email contains text 'This is a confirmation of your order'
And email contains text 'The current status of your order has been changed to "Completed'
