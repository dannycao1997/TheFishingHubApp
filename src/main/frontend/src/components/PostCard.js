import React from 'react';

const PostCard = ({ post }) => {
    return (
        <div className="card mb-3">
            <div className="card-header">
                Posted by {post.username} on {new Date(post.date).toLocaleDateString()}
            </div>
            <div className="card-body">
                <p className="card-text">{post.content}</p>
            </div>
            <div className="card-footer">
                <button className="btn btn-primary">Like</button>
                <button className="btn btn-secondary">Comment</button>
            </div>
        </div>
    );
};

export default PostCard;
