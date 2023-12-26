public class Validator {
    public boolean matchNumber(String number){
        return number.matches("^[0-9]*$");
    }
    public boolean matchCharacters(String characters){
        return characters.matches("^[a-zA-Z ]*$");
    }
    public boolean matchCharactersOnly(String characters){
        return characters.matches("^[a-zA-Z]*$");
    }
    public boolean matchCharactersAndDot(String experison){
        return experison.matches(("^[a-zA-Z .]*$"));
    }
    public boolean matchCharactersAndNumber(String experison){
        return experison.matches(("^[a-zA-Z0-9]*$"));
    }
    public boolean matchNicOld(String nic){
        return nic.matches("^\\d{9}[VX]$");
    }
    public boolean matchNicNew(String nic){
        return nic.matches("^\\d{12}$");
    }
    public boolean matchNic(String nic){
        return matchNicNew(nic) || matchNicOld(nic);
    }
    public boolean matchDate(String date)
    {
        DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");        ;
        return date.matches("\\d{4}-\\d{2}-\\d{2}") && validator.isValid(date);
    }
    public boolean matchTelNo(String tel){
        return tel.matches("\\d{10}");
    }
    public boolean matchSimpleCharactors(String characters){
        return characters.matches(".*[a-z].*");
    }
    public boolean matchCapicalCharactors(String characters){
        return characters.matches(".*[a-z].*");
    }
    public boolean matchSpecialCharacters(String characters){
        return characters.matches(".*[&%$#@^*?+\\-=!:;./~<>\\[\\]{}].*");
    }
    public boolean matchANumber(String characters){
        return characters.matches(".*[0-9].*");
    }
}

