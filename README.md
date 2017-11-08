<!--
	Markdown
	
	Copyright 2017 IS2T. All rights reserved.
	Modification and distribution is permitted under certain conditions.
	
	IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
-->

# Overview
This test has several features:
* The **Threshold** test page tests the touch press threshold on a UI object, like a button.
* The **Filtering** test page tests the touch drag event, with the display flickering if a drag event was detected.
* The **Calibration** test page tests the touch coordinates, displaying a grid on the whole screen and printing the coordinates of a touch press.
* The **Drag** test page tests the touch drag event, by displaying all the intermediary touch points between a press and a release
* The **Target** test page tests the touch coordinates, display a target randomly on the screen and printing the delta between the coordinates of a touch press and the center of target.

# Requirements
* MicroEJ SDK 4.0 or later
* Java JDK 1.7 or later 

## Run on device
### Build
1. Right Click on the project.
1. Select **Run as -> Run Configuration**
1. Select **MicroEJ Application** configuration kind
1. Click on **New launch configuration** icon
1. In **Execution** tab
	1. In **Target** frame, in **Platform** field, select a platform
	1. In **Execution** frame
		1. Select **Execute on Device**
1. Press **Apply**
1. Press **Run**

# Dependencies
_All dependencies are retrieved transitively by Ivy resolver_.

# Source
N/A

# Restrictions
None.