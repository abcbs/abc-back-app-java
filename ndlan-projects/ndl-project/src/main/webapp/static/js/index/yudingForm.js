$(document).ready(function(){
	
	$("#peopleNum,#sudokuSelect").sudoku();
	
	$("#prepay,#prepaySudokuSelect").sudoku();
	
	$("#orderTime,#timeSelectBtn").timeSelect();
	
	
	$("#popSave").bind("click",savePop);
});


function savePop()
{
	$("#popSaveForm").submit();
}