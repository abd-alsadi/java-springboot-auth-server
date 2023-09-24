package com.core.authserver.models.ui;
import java.util.*;
import lombok.*;

public class QueryModel {


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Data
    public static class ColumnData {
        private String db;
        private String type;
        private String table;
    }
    
    public static String AccessListTableName="access_list";
    public static String AccessListPrefixColumnsName="access_list_$$$_";

    public static String ClientTableName="oauth_client_details";
    public static String ClientPrefixColumnsName="client_details_$$$_";

	public static String ObjectAccessListTableName="object_access_list";
    public static String ObjectAccessListPrefixColumnsName="object_access_list_$$$_";

    public static String RoleTableName="role";
    public static String RolePrefixColumnsName="role_$$$_";

    public static String UserTableName="users";
    public static String UserPrefixColumnsName="user_$$$_";

	
    
    public static final String  AccessListBaseQuery= "select "+AccessListTableName+".*,"+ObjectAccessListTableName+".*,"+RoleTableName+".*,"+UserTableName+".* "+
                                                     "from "+AccessListTableName+" inner join "+ ObjectAccessListTableName+" on ("+AccessListTableName+"."+AccessListPrefixColumnsName+"object_id="+ObjectAccessListTableName+"."+ObjectAccessListPrefixColumnsName+"id) "+
                                                     "left join "+RoleTableName+" on ("+AccessListTableName+"."+AccessListPrefixColumnsName+"role_id="+RoleTableName+"."+RolePrefixColumnsName+"id) "+
                                                     "left join "+UserTableName+" on ("+AccessListTableName+"."+AccessListPrefixColumnsName+"user_id="+UserTableName+"."+UserPrefixColumnsName+"id) ";

	public static final String  AccessListBaseQueryCount= "select count(*) "+
                                                    "from "+AccessListTableName+" inner join "+ ObjectAccessListTableName+" on ("+AccessListTableName+"."+AccessListPrefixColumnsName+"object_id="+ObjectAccessListTableName+"."+ObjectAccessListPrefixColumnsName+"id) "+
                                                    "left join "+RoleTableName+" on ("+AccessListTableName+"."+AccessListPrefixColumnsName+"role_id="+RoleTableName+"."+RolePrefixColumnsName+"id) "+
                                                    "left join "+UserTableName+" on ("+AccessListTableName+"."+AccessListPrefixColumnsName+"user_id="+UserTableName+"."+UserPrefixColumnsName+"id) ";
	
    
    public static final String  ClientBaseQuery= "select "+ClientTableName+".* from "+ClientTableName+" ";
	public static final String  ClientBaseQueryCount= "select count(*) from "+ClientTableName+" " ;

    
    public static final String  ObjectAccessListBaseQuery= "select "+ObjectAccessListTableName+".*,"+ClientTableName+".* "+
                                                   "from "+ObjectAccessListTableName+" inner join "+ClientTableName+" on ("+ObjectAccessListTableName+"."+ObjectAccessListPrefixColumnsName+"client_id="+ClientTableName+"."+ClientPrefixColumnsName+"id) " ;
	public static final String  ObjectAccessListBaseQueryCount= "select count(*) "+
                                                    "from "+ObjectAccessListTableName+" inner join "+ClientTableName+" on ("+ObjectAccessListTableName+"."+ObjectAccessListPrefixColumnsName+"client_id="+ClientTableName+"."+ClientPrefixColumnsName+"id) " ;

    
    public static final String  RoleBaseQuery= "select "+RoleTableName+".*,"+ClientTableName+".* "+
                                                "from "+RoleTableName+" inner join "+ClientTableName+" on ("+RoleTableName+"."+RolePrefixColumnsName+"client_id="+ClientTableName+"."+ClientPrefixColumnsName+"id) " ;
	
    public static final String  RoleBaseQueryCount= "select count(*) "+
                                                "from "+RoleTableName+" inner join "+ClientTableName+" on ("+RoleTableName+"."+RolePrefixColumnsName+"client_id="+ClientTableName+"."+ClientPrefixColumnsName+"id) " ;


	public static final String  UserBaseQuery= "select "+UserTableName+".*,"+RoleTableName+".* "+
                                                "from "+UserTableName+" inner join "+RoleTableName+" on ("+UserTableName+"."+UserPrefixColumnsName+"role_id="+RoleTableName+"."+RolePrefixColumnsName+"id) " ;
	
    public static final String  UserBaseQueryCount= "select count(*) "+
                                                "from "+UserTableName+" inner join "+RoleTableName+" on ("+UserTableName+"."+UserPrefixColumnsName+"role_id="+RoleTableName+"."+RolePrefixColumnsName+"id) " ;



    public static final Map<String, ColumnData> AccessListMAP = new HashMap<>();
	static {
		AccessListMAP.put("id",new ColumnData(AccessListPrefixColumnsName+"id","uid",AccessListTableName));
		AccessListMAP.put("permissions",new ColumnData(AccessListPrefixColumnsName+"permissions","int",AccessListTableName));
		AccessListMAP.put("roleId",new ColumnData(AccessListPrefixColumnsName+"role_id","uid",AccessListTableName));
		AccessListMAP.put("userId",new ColumnData(AccessListPrefixColumnsName+"user_id","uid",AccessListTableName));
		AccessListMAP.put("objectId",new ColumnData(AccessListPrefixColumnsName+"object_id","uid",AccessListTableName));
		AccessListMAP.put("type",new ColumnData(AccessListPrefixColumnsName+"type","string",AccessListTableName));
        AccessListMAP.put("userName",new ColumnData(UserPrefixColumnsName+"user_name","string",UserTableName));
        AccessListMAP.put("roleName",new ColumnData(RolePrefixColumnsName+"name","string",RoleTableName));
        AccessListMAP.put("objectName",new ColumnData(ObjectAccessListPrefixColumnsName+"name","string",ObjectAccessListTableName));
	}

    public static final Map<String, ColumnData> ClientMAP = new HashMap<>();
	static {
		ClientMAP.put("id",new ColumnData(ClientPrefixColumnsName+"id","uid",ClientTableName));
		ClientMAP.put("clientId",new ColumnData(ClientPrefixColumnsName+"client_id","string",ClientTableName));
		ClientMAP.put("clientSecret",new ColumnData(ClientPrefixColumnsName+"client_secret","string",ClientTableName));
		ClientMAP.put("accessTokenValidity",new ColumnData(ClientPrefixColumnsName+"access_token_validity","int",ClientTableName));
		ClientMAP.put("authorities",new ColumnData(ClientPrefixColumnsName+"authorities","string",ClientTableName));
		ClientMAP.put("scope",new ColumnData(ClientPrefixColumnsName+"scope","string",ClientTableName));
		ClientMAP.put("authorizedGrantTypes",new ColumnData(ClientPrefixColumnsName+"authorized_grant_types","string",ClientTableName));
		ClientMAP.put("refreshTokenValidity",new ColumnData(ClientPrefixColumnsName+"refresh_token_validity","int",ClientTableName));
		ClientMAP.put("resourceIds",new ColumnData(ClientPrefixColumnsName+"resource_ids","string",ClientTableName));
		ClientMAP.put("webServerRedirectUri",new ColumnData(ClientPrefixColumnsName+"web_server_redirect_uri","string",ClientTableName));
		ClientMAP.put("addInfo",new ColumnData(ClientPrefixColumnsName+"additional_information","string",ClientTableName));
		ClientMAP.put("autoApprove",new ColumnData(ClientPrefixColumnsName+"autoapprove","string",ClientTableName));
	}

	public static final Map<String, ColumnData> ObjectAccessListMAP = new HashMap<>();
	static {
		ObjectAccessListMAP.put("id",new ColumnData(ObjectAccessListPrefixColumnsName+"id","uid",ObjectAccessListTableName));
		ObjectAccessListMAP.put("name",new ColumnData(ObjectAccessListPrefixColumnsName+"name","string",ObjectAccessListTableName));
		ObjectAccessListMAP.put("enabled",new ColumnData(ObjectAccessListPrefixColumnsName+"enabled","boolean",ObjectAccessListTableName));
		ObjectAccessListMAP.put("clientId",new ColumnData(ObjectAccessListPrefixColumnsName+"client_id","uid",ObjectAccessListTableName));
        ObjectAccessListMAP.put("clientName",new ColumnData(ClientPrefixColumnsName+"name","string",ClientTableName));
	}

	public static final Map<String, ColumnData> RoleMAP = new HashMap<>();
	static {
		RoleMAP.put("id",new ColumnData(RolePrefixColumnsName+"id","uid",RoleTableName));
		RoleMAP.put("name",new ColumnData(RolePrefixColumnsName+"name","string",RoleTableName));
		RoleMAP.put("clientId",new ColumnData(RolePrefixColumnsName+"client_id","uid",RoleTableName));
        RoleMAP.put("clientName",new ColumnData(ClientPrefixColumnsName+"name","string",ClientTableName));
	}



	public static final Map<String, ColumnData> UserMAP = new HashMap<>();
	static {
		UserMAP.put("id",new ColumnData(UserPrefixColumnsName+"id","uid",UserTableName));
		UserMAP.put("userName",new ColumnData(UserPrefixColumnsName+"user_name","string",UserTableName));
		UserMAP.put("password",new ColumnData(UserPrefixColumnsName+"pass","string",UserTableName));
		UserMAP.put("type",new ColumnData(UserPrefixColumnsName+"type","string",UserTableName));
		UserMAP.put("email",new ColumnData(UserPrefixColumnsName+"email","string",UserTableName));
		UserMAP.put("isAccountNonExpired",new ColumnData(UserPrefixColumnsName+"is_account_non_expired","boolean",UserTableName));
		UserMAP.put("isAccountNonLocked",new ColumnData(UserPrefixColumnsName+"is_account_non_blocked","boolean",UserTableName));
		UserMAP.put("isCredentialsNonExpired",new ColumnData(UserPrefixColumnsName+"is_credentials_non_expired","boolean",UserTableName));
		UserMAP.put("isEnabled",new ColumnData(UserPrefixColumnsName+"is_enabled","boolean",UserTableName));
		UserMAP.put("roleId",new ColumnData(UserPrefixColumnsName+"role_id","uid",UserTableName));
		UserMAP.put("roleName",new ColumnData(RolePrefixColumnsName+"name","string",RoleTableName));
		UserMAP.put("clientId",new ColumnData(RolePrefixColumnsName+"client_id","uid",RoleTableName));
	}

}
