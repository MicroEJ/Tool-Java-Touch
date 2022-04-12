<img src="https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/sdk_5.4.json" /> 
<img src="https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/arch_7.14.json" /> 
<img src="https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/gui_3.json" />

# Overview
This test has several features:
* The **Threshold** test page tests the touch press threshold on a UI object, like a button.
* The **Filtering** test page tests the touch drag event, with the display flickering if a drag event is detected.
* The **Calibration** test page tests the touch coordinates, displaying a grid on the whole screen and printing the coordinates of a touch press.
* The **Drag** test page tests the touch drag event, by displaying all the intermediary touch points between a press and a release.
* The **Target** test page tests the touch coordinates, displays a target randomly on the screen and prints the delta between the coordinates of a touch press and the center of target.

# Usage

## Run on MicroEJ Simulator

1. Right-click on the project,
2. Select **Run as -> MicroEJ Application**,
3. Select your platform, 
4. Press **OK**.

## Run on device

### Build

1. Right Click on the project,
2. Select **Run as > Run Configuration**,
3. Select **MicroEJ Application** configuration kind,
4. Click on **New launch configuration** icon,
5. In **Execution** tab:
	1. In **Target** frame, in **Platform** field, select a platform,
	2. In **Execution** frame:
		1. Select **Execute on Device**,
		2. In **Settings** field, select **Build & Deploy**,
6. Press **Apply**,
7. Press **Run**,
8. The MicroEJ application files (`microejapp.o`, `microejruntime.a`, include files) have been generated.
9. Build the BSP project of the device.


### Flash

1. Use the appropriate flashing tool.

# Requirements

This example has been tested on:

* MicroEJ SDK `5.4.0`.
* With the [STM32F7508-DK 1.3.2](https://github.com/MicroEJ/Platform-STMicroelectronics-STM32F7508-DK/tree/1.3.2) Platform that contains:

	* `EDC-1.3.3`.
	* `BON-1.4.0`.
	* `MICROUI-3.0.3`.
    
# Dependencies

_All dependencies are retrieved transitively by Ivy resolver_.

# Source

N/A

# Restrictions

None.


---
_Copyright 2017-2021 MicroEJ Corp. All rights reserved._
_Use of this source code is governed by a BSD-style license that can be found with this software._

