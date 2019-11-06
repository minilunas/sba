package com.ibm.security.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibm.security.entity.RspModel;
import com.ibm.security.entity.SbaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService {

//	@Autowired
//	private SecurityServiceClient securityclient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
//			Map<String,String> map = new HashMap();
//			map.put("email",username);
            ResponseEntity result = restTemplate.getForEntity("http://user/user/queryuser?email=" + username
                    , RspModel.class);

//			ResponseEntity<Object> result = securityclient.queryUser(username);
            RspModel accountresult = (RspModel) result.getBody();// getResult(result);

            if (accountresult.getCode() == 404) {
                throw new UsernameNotFoundException("用户 " + username + " 不存在");
            } else {
                SbaUser user = getResult(accountresult.getData());

                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
                return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UsernameNotFoundException(ex.getMessage());
        }

    }

    public SbaUser getResult(Object o) {
        Gson gson = new Gson();
        String jsonResultStr = gson.toJson(o);
        SbaUser user = gson.fromJson(jsonResultStr, SbaUser.class);
        return user;

    }

    public SbaUser getAccount(JsonObject result) {
        Gson gson = new Gson();

        SbaUser user = gson.fromJson(result.get("data").toString(), SbaUser.class);

        return user;

    }
}
