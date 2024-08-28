import { Button, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup, TextField } from '@mui/material';
import { ErrorMessage, Field, Form, Formik } from 'formik'
import React, { useState } from 'react'
import { useDispatch } from 'react-redux';
import * as Yup from "yup"
// import { registerUserAction } from '../../redux/Auth/auth.action';

const initialValues = { firstName: "", lastName: "", email: "", password: "" };
const validationSchema = {
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string().min(6, "Password must be at least 6 characters")
        .required("Password is required"),
};
const Register = () => {

    const [gender, setGender] = useState("");
    const dispatch = useDispatch();

    const handleSubmit = (values) => {
        values.gender = gender;


        // dispatch(registerUserAction({ data: values }));

    }

    const handleChange = (even) => {
        setGender(even.target.value);
    }
    return (
        <>
            <Formik
                onSubmit={handleSubmit}
                // validationSchema={validationSchema}
                initialValues={initialValues}>
                <Form className="space-y-5">
                    <div className='space-y-5'>
                        <div>
                            <Field
                                as={TextField}
                                name="firstName"
                                placeholder="FirstName"
                                type="Text"
                                variant="outlined"
                                fullWidth />

                            <ErrorMessage
                                name='firstName'
                                component="div"
                                className='text-red-50' />


                        </div>
                        <div>
                            <Field
                                as={TextField}
                                name="lastName"
                                placeholder="LastName"
                                type="Text"
                                variant="outlined"
                                fullWidth />

                            <ErrorMessage
                                name='lastName'
                                component="div"
                                className='text-red-50' />


                        </div>
                        <div>
                            <Field
                                as={TextField}
                                name="email"
                                placeholder="Email"
                                type="email"
                                variant="outlined"
                                fullWidth />

                            <ErrorMessage
                                name='email'
                                component="div"
                                className='text-red-50' />


                        </div>
                        <div>
                            <Field
                                as={TextField}
                                name="password"
                                placeholder="password"
                                type="password"
                                variant="outlined"
                                fullWidth />

                            <ErrorMessage
                                name='password'
                                component="div"
                                className='text-red-50' />
                        </div>
                        <div>
                            <FormControl>

                                <RadioGroup
                                    row
                                    aria-label="Gender"
                                    name="gender"
                                    onChange={handleChange}
                                >
                                    <FormControlLabel value="female" control={<Radio />} label="Female" />
                                    <FormControlLabel value="male" control={<Radio />} label="Male" />


                                </RadioGroup>
                            </FormControl>
                        </div>
                        <Button sx={{ padding: ".8rem 0rem" }} fullWidth type="submit" variant="contained" color="primary">Register</Button>
                    </div>

                </Form>
            </Formik>
        </>
    )
}

export default Register