package ec.edu.ups.services.dto;

import ec.edu.ups.models.UserModel;

public class UserOutputDTO {
	public String id;
    public String firebaseUid;
    public String username;
    public String foto_perfil; // nullable
    public String email;
    public String rol;  
    
    public UserOutputDTO() {}
    public static UserOutputDTO fromModel(UserModel md) {
    			UserOutputDTO dto = new UserOutputDTO();
		dto.id = md.getId().toString();
		dto.firebaseUid = md.getFirebaseUid();
		dto.username = md.getDisplayName();
		dto.foto_perfil = md.getPicture();
		dto.email = md.getEmail();
		dto.rol = md.getRole();
		return dto;
   }
}
