/**
 * UserController
 *
 * @description :: Server-side logic for managing users
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
    
	create: function(req, res, next){
    var email = req.param('email')
       User.create(req.body).exec(function (err, user) {
        if (err) {
          return res.json(err.status, {err: err});
        }
        // If user created successfuly we return user and token as response
        if (user) {
          // NOTE: payload is { id: user.id}
          res.send('true');
        }
      });
        
    },
    edit: function(req, res, next){
        var email = req.param('email')
        User.findOne({email:email}).exec(function(err,user){
            if (err) {
                return res.serverError(err);
              }
              if (!user) {
                return res.notFound('Could not find email, sorry.');
              }
            
              //sails.log('Found "%s"', finn.fullName);
              return res.json(user);
            });
            
        },
        // User.findOne(req.param('email'), function foundUsers(err,user){
        //     if(err) return next(err);
        //     if(!user) return res.send(404,'User doesn\'t exist.');
        //      res.view({
        //         user: user
        //     });
        // });
    

    update: function(req, res, next){
        User.update(req.param('id'),req.params.all(), function userUpdated(err,user){
            if(err){
                return res.redirect('/user/' + req.param('id'));
            }
			if(user)
				res.json(user);
        });
    },
    delete: function(req, res, next){
        var id = req.param('id');
        User.findOne(id, function foundUsers(err,user){
            if(err) return next(err);

            if(!user) return res.send(404,'User doesn\'t exist.');

            User.destroy(req.param('id'), function userDestroyed(err){
                if(err) return next(err);
            });
            res.send(200,'user id '+ id + ' has been deleted')
            //res.json(202,user);
        });
    },
    login : function(req,res){
        
    }
       
    
      
};

