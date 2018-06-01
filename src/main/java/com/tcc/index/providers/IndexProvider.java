package com.tcc.index.providers;

import com.google.gson.Gson;
import com.tcc.index.DTO.LoginDTO;
import com.tcc.index.LoginMapper;
import com.tcc.index.TADs.LoginInfo;
import com.tcc.index.TADs.LoginObject;
import com.tcc.index.TADs.Usuario;
import com.tcc.index.database.Login;
import com.tcc.index.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class IndexProvider {
    Gson gson = new Gson();

    @Autowired
    LoginRepository loginRepository;

    private int verify(LoginObject lo){
        if(lo.getCode().equals("lucas") && lo.getPassword().equals("12345")){
            return 0;
        }else if(lo.getCode().equals("p") && lo.getPassword().equals("1")){
            return 1;
        }else{
            return 2;
        }
    }

    @PostMapping(value = "/login")
    public String verifyLogin(@RequestBody String login){
        LoginObject loginO = gson.fromJson(login, LoginObject.class);
        Login l = loginRepository.verifyLogin(loginO.getCode());

        LoginDTO log;

        if(l == null || (!l.getPassword().equals(loginO.getPassword())) ){
            log = new LoginDTO(-1L,-1,"");
        }else{
            log = LoginMapper.EntitytoDTO(l);
        }

        return gson.toJson(log);
    }

    @GetMapping(value = "/getLoginInfo")
    public @ResponseBody String getLoginInfo(){
        System.out.println("PEGANDO LOGININFO");
        ArrayList<LoginInfo> infos = new ArrayList<LoginInfo>();

        infos.add(new LoginInfo("Codigo Fonte Aberto","Possibilita a  melhoria e modificação de acordo com as necessidades da instituição.","https://lh3.googleusercontent.com/p4-YtT3fjFHXv8wLD2AQE4cdlZo-O9d0uNNrQ12mD7BaQZQxVMqhuvWMMbwQDtqMRo8fHKzr-vfMTQ9QE6-eTex82a3eyIao7xDjb_4I5iPyQavJKeDmX-FVA1UXp3CHNlCN6NAzEdzryQXz4TEH8LPm9ZJJEq-kfKJcyZjwlmytiCqH2y4GhI8sQ77A7nbBJW7FVstqGocnCKrSxZ0usCKYB3qMxK2SB1VXwUHGaONQsQbqtJk2_SkgTUkq_xqEjZcgNfqmBMzHwPtk1OJQij5A9m5gFZ8ZFBW7P-p9EQ2CGDL9mC2JWJSiUs7kMCz-KYrnJ7DKAQ2mATXaA7r9v_N24BTe4UFlhAqRnwsEqlYof1O4z2LXulJx0ZK8uTi8kU_utHyJ87deNny0n92VNLhczMvR61zFA1z8381sxcGTI3NthfQcjW4YSMjrr0VFGVmH5SxfKvtq6tKICecUxsqQTA36rj2rizi0XHV0mN6rrLj6kmQnYFCEOCbZ3CK_e8uZvIc-8TIzLjkK5Sl0SdpTe3NHDefTe0o0Ctmgnkd1pmVhM6hgZoG0zMAVgwFjgeeeU5ZbIWdcmhMzUNswdTAWXoUZFFUWGsIjtGI=w1222-h880-no"));
        infos.add(new LoginInfo("Bootstrap Framework","Interface refinada e intuitiva com o Bootstrap, o framework mais utilizado atualmente por desenvolvedores web. Ele disponibiliza diversas ferramentas para possibilitar uma utilização mais fluida e intuitiva ao usuario.","https://lh3.googleusercontent.com/y4W_lchK1uVZ1yHo56b20Va2vgVdeewqvjS6mJe_Fw1__q2qDpGbDREjZUoqYAhTAiM8aeQRJyg51bgNntacNig4c7aM5hB1ZNCYTsMVLyJ9nlSkLtV7gMZnu4DMzND4QNjfrrRrU5mqF8rRsz-qDb2RJSUM_9QUFrJlS4Qx_-mEmFS5QWurwYgquZLfqXnDEl7E2jUen_bP1fwetrerNF5CYj26wvOewx9_wZ__4hQzby8iQ4n6OvddWHfSx1pzInLVwT-bcTr2ZN5lEc2GceNld4l6xzb4XUQhWML5VlRePUleEZx73EydgZ0H8hpwZUZIKK4jhFxIWbDM5etPCn2dp5XkNyqiA-4vsy3J4B7_EweQymBj-AmYIbPEBtLpuxrVRQBBi8KJ432vuZKGbVXCVjN9plR4xZwj3weX4TkS5tt6Irkl8dWAGqt3gbIFpck-Qzsy8x268yDnMZZ3j3aW7r0X8StnF7CPhuzGfBAPFUMv6TLZ3BF0vQczLCB8k9vjDSZwTmL40Jqi1IpPBJIXy2PangMVPDnLQ8ok6d9N_WOkT01BaBsRihaheVOny2m6Hq4hxXCIzScnDJA0h1kjGTyGF2fVNCdaqXs=w1223-h880-no"));
        infos.add(new LoginInfo("Sistema Robusto","Com a utilização de Microservices, é possivel realizar manutenção em areas especificas do sistema, sem comprometer as demais. Isto gatante mais robustez para o sistema. E evita que ele fique totalmente fora do ar em caso de falhas.","https://lh3.googleusercontent.com/wnzmukFDMLvVvBsYfcTdcQa31QhDyYLB9DVtWlyiBdCiHb4sltIQmIEoSQhqCWsMlO3T2fq7GnL5DEr6AaWRFh0BpN4hMJqurP2e4I1I0rq_Ci4Yl2HH0FGYMF1tU_9RFuf6Sd6rL16QSHpDEF82RNqCcDhXnIfqRK99dcw9mN4y0Y0RaZuJ1rg_3pFti2hKA7YXtXAMw56ksb0i201t_7FzwaU0dSS153ruTAjkzaxgVRRY76rT3gcWViZX0kPUtZMr0Ef_VYTQiROdAIzwAa5lx5ZARO-LC1JeP-55bw8DpA089y1ncHMEkGUp6rosYsDxtJ5YP_uG3clICoioBUYLj0qV3K-OlADF5mH8zs0ujsvKFipY5vZkLM1-vdcJLvPT8JG_dfvB-eMymUdsnLd_hzpbrks3UckIvyLp17MwuLaJWsxBlX8_gYQ2QZFmQUgABfKIUJrEGGKpa_e8OYU5X9fMz1PNK_r1Blo3cuXKyw74zsk0tE1nwtYBAlWj-FRZeafPtKKvyye4Ka76qxjfcOHy7Ld7KLiLsWJ7sXbEcCJu1VrPX4C9FQcJTXn0p_w67ziC5MWp_CJBAD4GocrR2OStOXMFFrbSrP4=w1250-h900-no"));

        return gson.toJson(infos);
    }
}
