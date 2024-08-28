import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { Formik, useFormik } from 'formik';
import { updateProfileAction } from '../../redux/Auth/auth.action';
import { Avatar, IconButton, TextField } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600,
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 2,
  outline: "none",
  overFlow: "scroll-y",
  borderRadius: 3
};

export default function ProfileModal({ open, handleClose, handleOpen }) {
  const dispatch = useDispatch();
  const { auth } = useSelector(store => store)

  const formik = useFormik({
    initialValues: {
      firstName: "",
      lastName: ""
    },
    onSubmit: (values) => {

      dispatch(updateProfileAction(values))
      handleClose();
    },
  })
  return (
    <div>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
            <div className='flex items-center justify-between'>
              <div className='flex items-center space-x-3'>
                <IconButton onClick={handleClose}>
                  <CloseIcon />
                </IconButton>
                <p>Edit Profile</p>
              </div>
              <Button type='submit' >Save</Button>
            </div>
            <div>
              <div className='h-[15rem]'>
                <img className='w-full h-full rounded-t-md'
                  src='https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2024/01/midjourney-desktop-background-of-a-cottage-by-the-sea.jpg?q=50&fit=crop&w=1100&h=618&dpr=1.5'
                  alt='Man avatar' />
              </div>
              <div className='px-5 flex justify-between items-start mt-5 h-[5rem]' >
                <Avatar className='transform -translate-y-24'
                  sx={{ width: "10rem", height: "10rem" }} src='' alt='' />


              </div>
            </div>
            <div className='space-y-3 ' >
              <TextField
                placeholder={auth?.user.firstName}
                fullWidth
                id='firstName'
                name='firstName'
                label="First Name"
                value={formik.values.firstName}
                onChange={formik.handleChange}
              />
              <TextField
                placeholder={auth?.user.lastName}
                fullWidth
                id='lastName'
                name='lastName'
                label="Last Name"
                value={formik.values.lastName}
                onChange={formik.handleChange}
              />

            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}
