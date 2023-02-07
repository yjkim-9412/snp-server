import React, {useEffect, useState} from 'react';
import {StudentType} from "../../interface/StudentFieldType";
import {FormControl, InputLabel, Paper, Select, SelectChangeEvent, Stack, styled} from "@mui/material";
import MenuItem from "@mui/material/MenuItem";
import {Button, Grid} from "@mui/material/";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import AppBarComp from "../AppBarComp";
import LessonTextField from "./LessonTextField";
import LessonTextFieldOut from "./LessonTextFieldOut";
import {useNavigate} from "react-router-dom";
import InfoModal from "../student/InfoModal";
import CssBaseline from "@mui/material/CssBaseline";
import ConcentrationField from "./ConcentrationField";
import EyeballPractice from "./EyeballPractice";

const TextFields = styled(TextField)`
   input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    .MuiFormHelperText-root {
  color: #FF0000 !important;
}
`;

type StudentStudyType = {
    dayCount: string
    study: string[],
    dayOfStudy?: DayOfStudyType

}
type DayOfStudyType = {
    studentId: string,
    studyId: string,
    studyDetail: string,
    currentStudyCount: string,
    studyType: string,
    lastDate: string,
    studentInfo?: StudentType,
    studyTypeString: string,
}
type LogType ={
    id: string,
    studentId: string,
    StudyDetail: string,
    studyCount: string,
    concentration: string,
    concentrationAnswer: string,
    rapidEyeball: string,
    eyeBallCount: string,
    figureOne: string,
    figureTwo: string,
    textBookCode: string,
    intelligibility: string,
    processingTime: string,
    readCount: string,
    memo: string,
    studyType: string,
    dayOfWeek:string
}

const LessonRegister: React.FC<StudentStudyType> = ({dayCount, study, dayOfStudy}) => {
    const navigate = useNavigate();

    const [logForm, setLogForm] = useState({
        id: '',
        studentId: '',
        studyDetail: '',
        studyCount: '',
        concentration: '',
        concentrationAnswer: '',
        rapidEyeball: '',
        eyeBallCount: '',
        figureOne: '',
        figureTwo: '',
        textBookCode: '',
        intelligibility: '',
        processingTime: '',
        readCount: '',
        memo: '',
        studyType: '',
        dayOfWeek: ''
    });
    const [dayOfStudyValue, setDayOfStudyValue] = useState<DayOfStudyType>({
        studentId: '',
        studyId: '',
        studyDetail: '',
        currentStudyCount: '',
        studyType: '',
        lastDate: '',
        studentInfo: undefined,
        studyTypeString: '',
    });
    const [studyValue, setStudyValue] = useState<string[]>([])
    const [studyDetail, setStudyDetail] = useState('');
    const [studentInfo, setStudentInfo] = useState<StudentType>();
    const [grade, setGrade] = useState('');

    const onChange = (name:string, value:string) => {
        setLogForm({...logForm,[name]:value})
    }

    useEffect(() => {
        if (study !== undefined) {
            setStudyValue(study);
            if (dayOfStudy !== undefined && dayOfStudy.studentInfo !== undefined) {
                let studentInfo = dayOfStudy.studentInfo;
                setDayOfStudyValue(dayOfStudy);
                setStudyDetail(dayOfStudy.studyDetail);
                setStudentInfo((prevState) => {
                    return prevState = studentInfo
                });
                setGrade(studentInfo.gradeToString + ' ' + studentInfo.gradeLv)
                setLogForm({...logForm,studyCount: dayOfStudy.currentStudyCount, studentId: studentInfo.id,
                    studyDetail: dayOfStudy.studyDetail
                })
            }
        }
    }, [study])
    const onChangeSelect = (e: SelectChangeEvent) => {
        setStudyDetail(e.target.value);
    }
    console.log(logForm)

    return (

        <Paper
            sx={{
                p: 2,
                display: 'flex',
                flexDirection: 'column'
            }}
        >
            <CssBaseline/>
            <AppBarComp typography={'수업자료 등록'}/>
            <FormControl component="fieldset" variant="standard">
                <Grid container spacing={2}>
                    <Grid item xs={12} sx={{marginTop: 2}}>
                        <Stack
                            direction="row"
                            divider={<Divider orientation="vertical" sx={{bgcolor:'grey'}} flexItem/>}
                            spacing={2}
                        >
                            <LessonTextField widthValue={85} name={'name'}  value={studentInfo?.name} label={'학생이름'}/>
                            <LessonTextField widthValue={85} name={'grade'} value={grade}
                                             label={'구분'}/>
                            <LessonTextField widthValue={85} name={'parentName'} value={studentInfo?.parentName}
                                             label={'학부모 이름'}/>
                            <LessonTextField widthValue={130} name={'parentPhone'} value={studentInfo?.parentPhone}
                                             label={'학부모 연락처'}/>
                            <InfoModal studentInfo={studentInfo}/>
                        </Stack>
                    </Grid>
                    <Grid item xs={12}>
                        <Stack
                            direction="row"
                            divider={<Divider orientation="vertical" sx={{bgcolor:'grey'}} flexItem/>}
                            spacing={2}
                        >
                            <FormControl size="small">
                                <InputLabel id="day">{dayOfStudyValue.studyTypeString}</InputLabel>
                                <Select
                                    id="day"
                                    value={studyDetail}
                                    label={dayOfStudyValue.studyTypeString}
                                    onChange={onChangeSelect}
                                    sx={{minWidth: 160, maxWidth: 120}}
                                    style={{marginBottom: 1}}

                                > <MenuItem disabled={true} value=''>코스선택</MenuItem>
                                    {studyValue.map((item, index) => {
                                        return <MenuItem key={index} value={item}>{item}</MenuItem>
                                    })}
                                </Select>
                            </FormControl>
                            <LessonTextFieldOut widthValue={90} value={dayOfStudyValue.currentStudyCount}
                                                name={'currentStudyCount'} label={'일수'}/>
                            <ConcentrationField name={'concentration'} getValue={logForm.concentration}
                                                answer={logForm.concentrationAnswer} onChange={onChange}/>
                            <EyeballPractice getRapidEyeball={logForm.rapidEyeball} getEyeBallCount={logForm.eyeBallCount} onChange={onChange}/>
                        </Stack>
                    </Grid>
                </Grid>
            </FormControl>

        </Paper>
    )
        ;
}

export default LessonRegister;