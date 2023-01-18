import React, {useState} from 'react';
import {createTheme} from "@mui/material/styles";
import {CssBaseline, FormHelperText, Grid, styled, TextField, ThemeProvider, Typography} from "@mui/material";
import {Box, Container, FormControl} from "@mui/material/";
import ChangeProps from "../../interface/ChangeProps";
import TextFieldsCpt from "../TextFieldsCpt";

const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;
const TextFields = styled(TextField)`
   input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
`;


const StudentRegister: React.FC = () => {
    const theme = createTheme();
    const [address, setAddress] = useState({'city': '', 'street': ''});
    const [skill, setSkill] = useState({'speed': '', 'readLv': '', 'intLv': ''});
    const [studentSave, setStudentSave] = useState({
        "name": '', "age": '', "birth": '', "phone": '', 'email': '', 'parentName': '',
        'parentPhone': '', 'gender': '', 'studyType': '', 'grade': '', 'gradeLv': '',
        'teacherId': '', 'AddressForm': address,
        'SkillForm': skill
    });
    const onChange = (e: ChangeProps) => {
        let value: string = e.target.name;
        switch (value) {
            case "name":
                setStudentSave({...studentSave, name: e.target.value});
                break;
            case "age":
                setStudentSave({...studentSave, age: e.target.value});
                break;
            case "phone":
                setStudentSave({...studentSave, phone: e.target.value});
                break;
            case "email":
                setStudentSave({...studentSave, email: e.target.value});
                break;
            case "parentName":
                setStudentSave({...studentSave, parentName: e.target.value});
                break;
            case "parentPhone":
                setStudentSave({...studentSave, parentPhone: e.target.value});
                break;
            case "gender":
                setStudentSave({...studentSave, gender: e.target.value});
                break;
            case "birth":
                setStudentSave({...studentSave, birth: e.target.value});
                break;
            case "studyType":
                setStudentSave({...studentSave, studyType: e.target.value});
                break;
            case "grade":
                setStudentSave({...studentSave, grade: e.target.value});
                break;
            case "gradeLv":
                setStudentSave({...studentSave, gradeLv: e.target.value});
                break;
            case "teacherId":
                setStudentSave({...studentSave, teacherId: e.target.value});
                break;
            case "city":
                setAddress({...address, city: e.target.value});
                setStudentSave({...studentSave, AddressForm: address});
                break;
            case "street":
                setAddress({...address, street: e.target.value});
                setStudentSave({...studentSave, AddressForm: address});
                break;
            case "speed":
                setSkill({...skill, speed: e.target.value});
                setStudentSave({...studentSave, SkillForm: skill});
                break;
            case "readLv":
                setSkill({...skill, readLv: e.target.value});
                setStudentSave({...studentSave, SkillForm: skill});
                break;
            case "intLv":
                setSkill({...skill, intLv: e.target.value});
                setStudentSave({...studentSave, SkillForm: skill});
                break;
        }

    }
return (
    <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <Box
                sx={{
                    marginTop: 20,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    학생등록
                </Typography>
                <Box component="form" noValidate sx={{mt: 3}}>
                    <FormControl component="fieldset" variant="standard">
                        <Grid container spacing={2}>
                            <Grid item xs={6}>
                                <TextField
                                    required
                                    autoFocus
                                    fullWidth
                                    type="text"
                                    id="name"
                                    name="name"
                                    label="학생 이름"
                                    value={studentSave.name}
                                    onChange={onChange}
                                    // error={emailError!== ''}
                                />
                                <FormHelperTexts></FormHelperTexts>
                            </Grid>
                            <Grid item xs={6}>
                                <TextFields
                                    required
                                    fullWidth
                                    type="number"
                                    id="age"
                                    name="age"
                                    label="나이"
                                    value={studentSave.age}
                                    onChange={onChange}
                                    // error={pwError!== ''}
                                />
                                <FormHelperTexts></FormHelperTexts>
                            </Grid>
                            {/**생일*/}
                            <TextFieldsCpt onChange={onChange} studentValue={studentSave.birth} textType={'birth'} labelType={'생년월일'}/>
                            {/*<TextFieldsCpt onChange={onChange} studentValue={studentSave.phone}/>*/}
                            {/*<TextFieldsCpt onChange={onChange} studentValue={studentSave.email}/>*/}
                            {/*<TextFieldsCpt onChange={onChange} studentValue={studentSave.parentName}/>*/}
                            {/*<TextFieldsCpt onChange={onChange} studentValue={studentSave.parentPhone}/>*/}
                        </Grid>
                    </FormControl>
                </Box>
            </Box>
        </Container>
    </ThemeProvider>
)
}
export default StudentRegister;