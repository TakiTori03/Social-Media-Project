import { Avatar, Card, IconButton } from '@mui/material'
import React, { useEffect, useState } from 'react'
import AddIcon from '@mui/icons-material/Add';
import StoryCicrle from './StoryCicrle';
import ImageIcon from '@mui/icons-material/Image';
import ArticleIcon from '@mui/icons-material/Article';
import VideocamIcon from '@mui/icons-material/Videocam';
import PostCard from '../Post/PostCard';
import CreatePostModal from '../CreatePost/CreatePostModal';
import { useDispatch, useSelector } from 'react-redux';
import { getAllPostAction } from '../../redux/Post/post.action';

const story = [11, 111, 1, 11, 2];
const post = [1, 11, 1, 1, 11];
const MiddlePart = () => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getAllPostAction())
    }, [])
    const { post } = useSelector(store => store);


    const [openCreatePostModal, setOpenCreatePostModal] = useState(false);

    const handleCloseCreatePostModal = () => {
        setOpenCreatePostModal(false);
    }

    const handleOpenCreatePostModal = () => {
        setOpenCreatePostModal(true);
    }
    const handleOpenCreateModel = () => {

    }

    return (
        <div className='px-20'>
            <section className=' flex items-center p-5 rounded-b-md'>
                <div className='flex flex-col items-center mr-4 cursor-pointer'>
                    <Avatar
                        sx={{ width: "5rem", height: "5rem" }}
                        src=''>
                        <AddIcon sx={{ fontSize: "3rem" }} />
                    </Avatar>
                    <p>New</p>
                </div>
                {story.map(item => <StoryCicrle />)}
            </section>
            <Card className="p-5 mt-5">
                <div className='flex justify-between'>
                    <Avatar />
                    <input
                        onClick={handleOpenCreatePostModal}
                        readOnly
                        className="outline-none w-[90%] bg-slate-100 rounded-full px-5 
                            bg-transparent border-[#3b4054] border"
                        type='text' />
                </div>
                <div className='flex justify-center space-x-9 mt-5'>
                    <div className='flex items-center'>
                        <IconButton color='primary' onClick={handleOpenCreateModel}>
                            <ImageIcon />
                        </IconButton>
                        <span>media</span>
                    </div>

                    <div className='flex items-center'>
                        <IconButton color='primary' onClick={handleOpenCreateModel}>
                            <VideocamIcon />
                        </IconButton>
                        <span>video</span>
                    </div>

                    <div className='flex items-center'>
                        <IconButton color='primary' onClick={handleOpenCreateModel}>
                            <ArticleIcon />
                        </IconButton>
                        <span>article</span>
                    </div>
                </div>
            </Card>
            <div className='mt-5 space-y-5'>
                {post.posts?.map(item => <PostCard item={item} />)}
            </div>
            <div>
                <CreatePostModal handleClose={handleCloseCreatePostModal} open={openCreatePostModal} />
            </div>
        </div>
    )
}

export default MiddlePart