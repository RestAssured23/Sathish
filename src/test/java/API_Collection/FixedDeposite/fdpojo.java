package API_Collection.FixedDeposite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class fdpojo {
    public static class Encryp{
        @Getter@Setter
        @JsonProperty("RequestEncryptdataResult")
        public String requestEncryptdataResult;
    }
    @Getter@Setter
    public static class Scheme{
        @JsonProperty("SchemeDetailResult")
        public String schemeDetailResult;
    }
}
