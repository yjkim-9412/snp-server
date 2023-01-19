import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {useState} from "react";
import PropsAction from "../../interface/PropsAction";
import TextFieldsCpt from "../TextFieldsCpt";
import {Modal, styled} from "@mui/material";
import Gender from "./Gender";
import {useNavigate} from "react-router-dom";
import AddressByKakao from "./AddressByKakao";



const TextFields = styled(TextField)`
   input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
`;
function Copyright(props: any) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
                Your Website
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const theme = createTheme();

export default function SignUpStudent() {
    const navigate = useNavigate();
    const [address, setAddress] = useState({'city': '', 'street': ''});
    const [skill, setSkill] = useState({'speed': '', 'readLv': '', 'intLv': ''});
    const [studentSave, setStudentSave] = useState({
        "name": '', "age": '', "birth": '', "phone": '', 'email': '', 'parentName': '',
        'parentPhone': '', 'gender': '', 'studyType': '', 'grade': '', 'gradeLv': '',
        'teacherId': '', 'AddressForm': address,
        'SkillForm': skill
    });

    const onClick = (e:React.MouseEvent<HTMLLabelElement, MouseEvent>,param:string) => {
        setStudentSave({...studentSave, gender: param});
    }
    const onChangeAddress = (e:{city:string, street:string}) => {
        setAddress({...address, street: e.street, city: e.city});
        setStudentSave({...studentSave, AddressForm: address});
    }


    const onChange = (e: PropsAction ) => {
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
    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        console.log({
            email: data.get('email'),
            password: data.get('password'),
        });
    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">

                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                       학생등록
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                {/**학생이름*/}
                                <TextFieldsCpt onChangeType={onChange} studentValue={studentSave.name}
                                               textType={'name'} labelType={'학생이름'}/>
                            </Grid>
                            <Grid item xs={12} sm={6}>
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
                            </Grid>
                            <Grid item xs={12} >
                                {/**성별*/}
                                <Gender onChangeRadio={onClick} studentValue={studentSave.gender} textType={'gender'} labelType={'성별'}/>
                            </Grid>
                            <Grid item xs={12} >
                                {/**연락처*/}
                                <TextFieldsCpt onChangeType={onChange} studentValue={studentSave.phone} textType={'phone'}
                                               labelType={'연락처'}/>
                            </Grid>
                            <Grid item xs={12} >
                                {/**이메일*/}
                                <TextFieldsCpt onChangeType={onChange} studentValue={studentSave.email} textType={'email'}
                                               labelType={'이메일'}/>
                            </Grid>
                            <Grid item xs={12} sm={6} >
                                {/**보호자*/}
                                <TextFieldsCpt onChangeType={onChange} studentValue={studentSave.parentName}
                                               textType={'parentName'} labelType={'보호자'}/>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <TextFieldsCpt onChangeType={onChange} studentValue={studentSave.parentPhone}
                                               textType={'parentPhone'} labelType={'보호자 연락처'}/>
                            </Grid>
                            <Grid item xs={12} >
                                <AddressByKakao onChangeAddress={onChangeAddress}/>
                            </Grid>
                            <Grid item xs={12} >
                            </Grid>
                            <Grid item xs={12} >
                            </Grid>
                            <Grid item xs={12} >
                            </Grid>
                            <Grid item xs={12}>
                                <FormControlLabel
                                    control={<Checkbox value="allowExtraEmails" color="primary" />}
                                    label="I want to receive inspiration, marketing promotions and updates via email."
                                />
                            </Grid>
                        </Grid>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6} >
                                <Button
                                    fullWidth
                                    variant="contained"
                                    sx={{ mt: 3, mb: 2 }}
                                    onClick={()=>navigate(-1)}
                                >
                                    취소
                                </Button>
                            </Grid>
                            <Grid item xs={12} sm={6} >
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    sx={{ mt: 3, mb: 2 }}
                                >
                                    학생등록
                                </Button>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}