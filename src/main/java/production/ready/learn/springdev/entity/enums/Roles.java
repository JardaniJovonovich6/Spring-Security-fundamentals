package production.ready.learn.springdev.entity.enums;

public enum Roles {

    USER,//GET ALL POSTS
    ADMIN,//CAN DO ANYTHING
    CREATOR,//CAN ONLY CREATE POSTS
    EDITOR,//CAN ONLY EDIT POSTS
    WATCHER//CAN SEE THE all the work done by other roles

}
