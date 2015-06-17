# Overview
This MicroEJ project helps to configure and test the touch panel:
- Filtering: drag events must not be generated when a finger is pressed but not moving.
- Calibration: position of touch panel on the screen.
- Drag distribution: the drag events position must be regularly distributed when swiping.

It is useful to run this project to test the BSP touch panel driver and make sure the events sent to the Java MicroUI library are correct.
 
## Project Setup
First of all, you have to download the repository by using the Download button or by cloning the repository.
After having retrieved the repository content, open your MicroEJ and then import Existing project into workspace by selecting either the ZIP file or the root directory.

### Requirements
- JRE 7 (or later) x86
- MicroEJ 3.1 or later
- The Java platform to test with at least: EDC 1.2 & MicroUI 1.5.

### Project structure
- `Tool-Java-Touch/src/main/java`: Java sources.
	- `com.is2t.pointer.framework`: test framework.
	- `com.is2t.pointer.tests`: all tests.

## Usage
To launch the application, right-click on the `com.is2t.pointer.tests.TestsPointer` class,
select `Run as`, `MicroEJ Application` and choose the platform to test.
To run it on the hardware, go to `Run` menu, `Run Configurations…`, choose the `TestsPointer` launch,
in `Execution` tab choose `Execute on EmbJPF` and `Run`.

Each test can be run independently: select the test and follow the same procedure.

## Changes
2015/06/15: initial version.

## License
See the license file `LICENSE.md` located at the root of this repository.
