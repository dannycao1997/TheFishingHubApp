import React, { useState, useEffect } from 'react';
import axios from 'axios';
import PostCard from './PostCard'; // We'll create this component next

const CommunityFeed = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        fetchPosts();
    }, []);

    const fetchPosts = async () => {
        try {
            const response = await axios.get('/api/posts'); // Adjust the API endpoint as needed
            setPosts(response.data);
        } catch (error) {
            console.error('Error fetching posts:', error);
        }
    };

    return (
        <div className="container mt-4">
            {posts.map(post => (
                <PostCard key={post.id} post={post} />
            ))}
        </div>
    );
};

export default CommunityFeed;
