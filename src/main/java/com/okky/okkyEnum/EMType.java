package com.okky.okkyEnum;

public enum EMType{
	errorNone(""), uidError("uid parameter not existed"), trashMoveError("error while moving file to trash directory"),
	alreadyDeleted("already deleted"), fileIOError("error while creating file"), thumbnailUpdateError("error because thumbnail update is not success")
	,boardTableUpdateError("error because board table update is not success"), dberror("db error"), boardTableInsertError("error because board table insert is not success") 
	,thumbnailInsertError("error because thumbnail insert is not success");
	final private String name;
	
	private EMType(String name) {
		this.name = name;
	}

}
