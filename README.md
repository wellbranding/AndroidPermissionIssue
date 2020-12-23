# AndroidPermissionIssue
This branch replicates permission issue which occurs after process death.
# Steps to reproduce this issue:
1. Enable don't keep activities in developers tools.
2. Launch application.
3. Don't tap any button in the permission dialog.
4. Go to the background to force process death.
5. Return to the application and state restoration should occur.
6. Permission dialog is still visible, but after pressing any button, for example Deny, **onRequestPermissionsResult is not being triggered**.

**OnRequestPermissionsResult should be triggered even after process death, but it is not**.
This issue was noticed since API 28.
