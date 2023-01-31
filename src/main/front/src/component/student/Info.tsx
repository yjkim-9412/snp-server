import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import {useEffect, useState} from "react";
import PropsAction from "../../interface/PropsAction";
import TextFieldsCpt from "../TextFieldsCpt";
import {Paper, styled} from "@mui/material";
import Gender from "../register/Gender";
import {useNavigate, useParams} from "react-router-dom";
import AddressByKakao from "../register/AddressByKakao";
import GradeOp from "../register/GradeOp";
import Skill from "../register/Skill";
import axios from "axios";
import Course from "../register/Course";
import {FormControl, FormHelperText} from "@mui/material/";
import RegistrationCheck from "../register/RegistrationCheck";
import Toolbar from "@mui/material/Toolbar";
import AppBar from "@mui/material/AppBar";
import Schedule from "./Schedule";
import AppBarComp from "../AppBarComp";


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
    .MuiFormHelperText-root {
  color: #FF0000 !important;
}
`;

type StudentType = {
    [student: string]: string;
}

const theme = createTheme();

export default function Info() {
    const navigate = useNavigate();
    const {id} = useParams();
    const [submitError, setSubmitError] = useState<string>('');
    const [ageError, setAgeError] = useState<boolean>(false);
    const [fieldError, setFieldError] = useState<StudentType>({
        "name": '', "age": '', "birth": '', "phone": '', 'email': '', 'parentName': '',
        'parentPhone': '', 'gender': '', 'studyType': '', 'grade': '', 'gradeLv': '',
        'city': '', 'street': '',
        'speed': '0', 'readLv': '0', 'intLv': '0'
    });
    const [infoForm, setInfoForm] = useState({
        "name": '', "age": '', "birth": '', "phone": '', 'email': '', 'parentName': '',
        'parentPhone': '', 'gender': '', 'studyType': '', 'grade': '', 'gradeLv': '',
        'address': '', 'registration': 'false',
        'speed': '0', 'readLv': '0', 'intLv': '0'
    });
    const [schedule, setSchedule] = useState();
    /**학생정보 받아오기 */
    useEffect(() => {
        axios.get(`/api/students/info/${id}`)
            .then(res => {
                setInfoForm(res.data);
            })
    },[])
    /**성별 onClick */
    const onClick = (e: React.MouseEvent<HTMLLabelElement, MouseEvent>, param: string) => {
        setInfoForm({...infoForm, gender: param});
    }
    /**select onChange */
    const onChangeSelect = (e: { name: string, value: string }) => {
        if (e.name === 'grade') {
            setInfoForm({...infoForm, grade: e.value})
        } else if (e.name === 'gradeLv') {
            setInfoForm({...infoForm, gradeLv: e.value})
        } else {
            setInfoForm({...infoForm, studyType: e.value})
        }
    }
    /**예비등록생 핸들러 */
    const onChangeSelectBoolean = (e: { name: string, value: string }) => {
        setInfoForm({...infoForm, registration: e.value});

    }
    /**주소 onChange */
    const onChangeAddress = (e: { address: string }) => {
        setInfoForm({...infoForm, address: e.address});
    }
    /**학생 나이 onChange*/
    const onChangeAge = (e: PropsAction) => {
        let name: string = e.target.name;
        let value: string = e.target.value
        const errorMessage = "필수 값 입니다";
        setInfoForm({...infoForm, [name]: value});
        if (value === '' || value === null) {
            setAgeError(true);
            setFieldError({...fieldError,[name]:errorMessage});
        }else {
            setAgeError(false);
            setFieldError({...fieldError,[name]:''});
        }

    }

    /**텍스트 onChange */
    const onChange = (e: PropsAction) => {
        let name: string = e.target.name;
        let value: string = e.target.value
        setInfoForm({...infoForm, [name]: value});
        setFieldError({...fieldError,[name]:''});


    }

    /** 빈값 텍스트필드 에러표시*/
    const setError = (e: string) => {
        const errorMessage = "필수 값 입니다";
        setFieldError({...fieldError, [e]: errorMessage})
        if (e === 'age') {
            setAgeError(true);
        }
    }

    /** submit 이벤트 핸들러*/
    const onSubmitStudent = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        for (const entryElement of Object.entries(infoForm)) {
            if (entryElement[1] === '' || entryElement[1] === null || entryElement[1] === undefined) {
                setSubmitError("필수 값을 입력해 주세요");
                setError(entryElement[0])
                return;
            }
        }
        await axios.post('/api/students/saveForm', infoForm).then((res) => {
                navigate('/');
            }
        ).catch(error => console.log(error));
    }

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <Grid container spacing={3}>
                <Grid  item md={8} lg={8}>

                    <Paper
                        sx={{
                            p: 2,
                            display: 'flex',
                            flexDirection: 'column'
                        }}
                    >
                        <AppBarComp typography={'학생 정보'}/>

                        <Box display="flex">

                            <Box component="form" noValidate onSubmit={onSubmitStudent} sx={{mt: 3}}>
                                <FormControl component="fieldset" variant="standard">
                                    <Grid container spacing={2}>
                                        <Grid item xs={12} sm={6}>
                                            {/**학생이름*/}
                                            <TextFieldsCpt onChangeType={onChange} studentValue={infoForm.name}
                                                           textType={'name'} labelType={'학생이름'}
                                                           fieldErrorType={fieldError.name}/>
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            <TextFields
                                                helperText={fieldError.age}
                                                required
                                                fullWidth
                                                type="number"
                                                id="age"
                                                name="age"
                                                label="나이"
                                                value={infoForm.age}
                                                onChange={onChangeAge}
                                                error={ageError}
                                            />
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            {/**성별*/}
                                            <Gender onChangeRadio={onClick} studentValue={infoForm.gender}
                                                    textType={'gender'}
                                                    labelType={'성별'} fieldErrorType={fieldError.gender}/>
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            <Box
                                                sx={{
                                                    display: 'flex',
                                                    flexDirection: 'column',
                                                    alignItems: 'center',
                                                }}
                                            >
                                                <Grid container spacing={1}>
                                                    {/**수업코스*/}
                                                    <Course onChangeSelect={onChangeSelect}
                                                            fieldErrorType={fieldError.studyType} courseProps={infoForm.studyType}/>
                                                    {/**예비등록 여부*/}
                                                    <RegistrationCheck onChangeSelect={onChangeSelectBoolean}/>
                                                </Grid>
                                            </Box>
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            {/**연락처*/}
                                            <TextFieldsCpt onChangeType={onChange} studentValue={infoForm.phone}
                                                           textType={'phone'}
                                                           labelType={'연락처'} fieldErrorType={fieldError.phone}/>
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            {/**생년월일*/}
                                            <TextFieldsCpt onChangeType={onChange} studentValue={infoForm.birth}
                                                           textType={'birth'}
                                                           labelType={'생년월일'} fieldErrorType={fieldError.birth}/>
                                        </Grid>
                                        <Grid item xs={12}>
                                            {/**이메일*/}
                                            <TextFieldsCpt onChangeType={onChange} studentValue={infoForm.email}
                                                           textType={'email'}
                                                           labelType={'이메일'} fieldErrorType={fieldError.email}/>
                                        </Grid>
                                        <Grid item xs={12} sm={12}>
                                            <GradeOp onChangeSelect={onChangeSelect} fieldErrorType={fieldError.grade}
                                            gradeLvProps={infoForm.gradeLv} gradeProps={infoForm.grade}/>
                                        </Grid>
                                        <Grid item xs={12}>
                                            <AddressByKakao onChangeAddress={onChangeAddress}
                                                            fieldErrorType={fieldError.city} addressProps={infoForm.address}/>
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            {/**학부모*/}
                                            <TextFieldsCpt onChangeType={onChange}
                                                           studentValue={infoForm.parentName}
                                                           textType={'parentName'}
                                                           labelType={'학부모'} fieldErrorType={fieldError.parentName}/>
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            {/**학부모*/}
                                            <TextFieldsCpt onChangeType={onChange}
                                                           studentValue={infoForm.parentPhone}
                                                           textType={'parentPhone'}
                                                           labelType={'학부모 연락처'}
                                                           fieldErrorType={fieldError.parentPhone}/>
                                        </Grid>
                                        <Grid item xs={12}>
                                            <Skill onChangeType={onChange} studentInt={infoForm.intLv}
                                                   studentReadLv={infoForm.readLv}
                                                   studentSpeed={infoForm.speed}/>
                                        </Grid>

                                    </Grid>
                                    <Grid container spacing={1}>
                                        <Grid item xs={1} sm={1}>
                                            <Button
                                                type="submit"
                                                fullWidth
                                                variant="contained"
                                                sx={{mt: 3, mb: 2}}
                                            >
                                                수정
                                            </Button>
                                        </Grid>
                                    </Grid>
                                </FormControl>
                                <FormHelperTexts style={{float: 'left'}}>{submitError}</FormHelperTexts>
                            </Box>
                        </Box>
                    </Paper>
                </Grid>
                <Grid item xs={12} md={4} lg={4}>
                    <Schedule studentId={id}/>
                </Grid>
            </Grid>
        </ThemeProvider>
    );
}