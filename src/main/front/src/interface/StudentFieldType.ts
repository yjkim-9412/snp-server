import PropsAction from "./PropsAction";
import React, {SyntheticEvent} from 'react';

export interface StudentFieldType {
    onChangeType: (e:React.ChangeEvent<HTMLInputElement> & EventTarget ) => void,
    studentValue: string
    textType: string
    labelType: string
}

export interface StudentFieldRadio{
    onChangeRadio: (e:React.MouseEvent<HTMLLabelElement, MouseEvent>, param:string) => void
    studentValue: string
    textType: string
    labelType: string
}

export interface StudentFieldAddress{
    onChangeAddress: (e:{city:string, street:string}) => void
}