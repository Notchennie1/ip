Mitch Chatbot - User Guide
Mitch is a CLI-based task manager designed for industrial designers and software developers to track project milestones and daily tasks efficiently.

Commands
1. Adding a ToDo: todo
   Adds a task without any specific date or time constraint.

Format: todo DESCRIPTION

Example: todo Research sustainable polymers for gaming mice

2. Adding a Deadline: deadline
   Adds a task that must be completed by a specific date or time.

Format: deadline DESCRIPTION /by TIME

Example: deadline Submit Homework /by Monday 5pm

3. Adding an Event: event /from /to
   Adds a task with a specific start and end time.

Format: event DESCRIPTION /from START /to END

Example: event project meeting /from Wednesday 2pm /to 4pm

4. Finding Tasks: find
   Search for existing tasks in your list using a keyword in the description.

Format: find KEYWORD

Example: find Razer

5. Listing All Tasks: list
   Displays every task currently stored in your session.

Format: list

6. Marking/Unmarking Tasks: mark & unmark
   Updates the completion status of a specific task using its index number from the list.

Format: mark INDEX or unmark INDEX

Example: mark 1

7. Deleting a Task: delete
   Permanently removes a task from your list using its index number.

Format: delete INDEX

Example: delete 2

8. Exiting: bye
   Safely closes the application.

Format: bye